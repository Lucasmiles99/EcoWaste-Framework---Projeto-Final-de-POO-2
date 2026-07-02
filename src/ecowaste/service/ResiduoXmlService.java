package ecowaste.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ecowaste.model.ResiduoEletronico;

/**
 * Serviço responsável pela geração automática de arquivos XML para resíduos eletrônicos.
 *
 * <p>
 * Esta classe cria um arquivo XML contendo os dados cadastrais de um
 * {@link ecowaste.model.ResiduoEletronico} e os caminhos dos arquivos de imagem
 * gerados pelo sistema.
 * </p>
 *
 * <p>
 * O XML produzido registra informações como identificador, nome, categoria, peso,
 * risco ambiental, valor estimado, moeda, localização, destino, status e referências
 * para os arquivos PNG, PPM, PGM, PBM e canais RGB.
 * </p>
 *
 * <p>
 * A geração automática de XML permite que o EcoWaste Framework mantenha uma
 * representação estruturada dos dados do resíduo, facilitando documentação,
 * rastreabilidade, interoperabilidade e futura integração com outros sistemas.
 * </p>
 *
 * <p>
 * Esta classe também realiza tratamento de caracteres especiais para evitar problemas
 * na estrutura XML, substituindo símbolos como {@code &}, {@code <}, {@code >},
 * aspas e apóstrofos por suas entidades correspondentes.
 * </p>
 *
 * <p>
 * No fluxo do sistema, esta classe é acionada após a geração dos arquivos de imagem,
 * produzindo um documento final que conecta os dados do cadastro às imagens geradas.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResiduoXmlService {

    public File gerarXml(ResiduoEletronico residuo, ResultadoArquivosResiduo arquivos) throws IOException {
        validar(residuo, arquivos);

        File pasta = new File("xml-gerados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String nomeBaseArquivo = gerarNomeBaseArquivo(residuo);

        File arquivoXml = new File(pasta, nomeBaseArquivo + ".xml");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoXml))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.newLine();

            writer.write("<residuo>");
            writer.newLine();

            escreverTag(writer, "id", residuo.getId());
            escreverTag(writer, "nome", residuo.getNome());
            escreverTag(writer, "categoria", residuo.getCategoria());
            escreverTag(writer, "peso", residuo.getPeso());
            escreverTag(writer, "riscoAmbiental", residuo.getRiscoAmbiental());
            escreverTag(writer, "valorEstimado", residuo.getValorEstimado());
            escreverTag(writer, "moeda", residuo.getMoeda());

            writer.write("    <localizacao>");
            writer.newLine();
            escreverTagInterna(writer, "cidade", residuo.getCidade());
            escreverTagInterna(writer, "estado", residuo.getEstado());
            escreverTagInterna(writer, "pais", residuo.getPais());
            escreverTagInterna(writer, "continente", residuo.getContinente());
            writer.write("    </localizacao>");
            writer.newLine();

            writer.write("    <descarte>");
            writer.newLine();
            escreverTagInterna(writer, "destino", residuo.getDestino());
            escreverTagInterna(writer, "status", residuo.getStatus());
            writer.write("    </descarte>");
            writer.newLine();

            writer.write("    <arquivos>");
            writer.newLine();
            escreverTagInterna(writer, "png", arquivos.getArquivoPNG().getPath());
            escreverTagInterna(writer, "ppm", arquivos.getArquivoPPM().getPath());
            escreverTagInterna(writer, "pgm", arquivos.getArquivoPGM().getPath());
            escreverTagInterna(writer, "pbm", arquivos.getArquivoPBM().getPath());
            escreverTagInterna(writer, "canalVermelho", arquivos.getArquivoCanalVermelho().getPath());
            escreverTagInterna(writer, "canalVerde", arquivos.getArquivoCanalVerde().getPath());
            escreverTagInterna(writer, "canalAzul", arquivos.getArquivoCanalAzul().getPath());
            writer.write("    </arquivos>");
            writer.newLine();

            writer.write("</residuo>");
            writer.newLine();
        }

        return arquivoXml;
    }

    private void escreverTag(BufferedWriter writer, String nomeTag, Object valor) throws IOException {
        writer.write("    <" + nomeTag + ">" + escaparXml(valor) + "</" + nomeTag + ">");
        writer.newLine();
    }

    private void escreverTagInterna(BufferedWriter writer, String nomeTag, Object valor) throws IOException {
        writer.write("        <" + nomeTag + ">" + escaparXml(valor) + "</" + nomeTag + ">");
        writer.newLine();
    }

    private String escaparXml(Object valor) {
        if (valor == null) {
            return "";
        }

        return String.valueOf(valor)
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    private void validar(ResiduoEletronico residuo, ResultadoArquivosResiduo arquivos) {
        if (residuo == null) {
            throw new IllegalArgumentException("O resíduo não pode ser nulo.");
        }

        if (arquivos == null) {
            throw new IllegalArgumentException("Os arquivos do resíduo não podem ser nulos.");
        }
    }

    private String gerarNomeBaseArquivo(ResiduoEletronico residuo) {
        String id = residuo.getId() != null ? String.valueOf(residuo.getId()) : "sem-id";

        String nomeTratado = residuo.getNome()
                .toLowerCase()
                .replace("ç", "c")
                .replace("ã", "a")
                .replace("á", "a")
                .replace("à", "a")
                .replace("é", "e")
                .replace("ê", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ô", "o")
                .replace("ú", "u")
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");

        return "residuo-" + id + "-" + nomeTratado;
    }
}