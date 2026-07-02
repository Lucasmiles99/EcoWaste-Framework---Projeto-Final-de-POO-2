package ecowaste.testevisual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import ecowaste.model.ResiduoEletronico;

public class GeradorImagemResiduo {

    private static final int LARGURA = 900;
    private static final int ALTURA = 500;

    public BufferedImage gerarImagem(ResiduoEletronico residuo) {
        BufferedImage imagem = new BufferedImage(
                LARGURA,
                ALTURA,
                BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g = imagem.createGraphics();

        configurarQualidade(g);
        desenharFundo(g);
        desenharCabecalho(g);
        desenharDadosResiduo(g, residuo);
        desenharRodape(g);

        g.dispose();

        return imagem;
    }

    private void configurarQualidade(Graphics2D g) {
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
    }

    private void desenharFundo(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, LARGURA, ALTURA);

        g.setColor(new Color(230, 245, 235));
        g.fillRect(0, 0, LARGURA, 110);

        g.setColor(new Color(0, 100, 70));
        g.fillRect(0, 0, LARGURA, 90);
    }

    private void desenharCabecalho(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 34));
        g.drawString("EcoWaste Framework", 40, 55);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Cartão digital de resíduo eletrônico", 40, 82);
    }

    private void desenharDadosResiduo(Graphics2D g, ResiduoEletronico residuo) {
        g.setColor(new Color(30, 30, 30));

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("Dados do Resíduo", 40, 150);

        g.setFont(new Font("Arial", Font.PLAIN, 18));

        int x = 40;
        int y = 190;
        int espaco = 32;

        g.drawString("ID: " + valor(residuo.getId()), x, y);
        y += espaco;

        g.drawString("Nome: " + valor(residuo.getNome()), x, y);
        y += espaco;

        g.drawString("Categoria: " + valor(residuo.getCategoria()), x, y);
        y += espaco;

        g.drawString("Peso: " + valor(residuo.getPeso()) + " kg", x, y);
        y += espaco;

        g.drawString("Risco Ambiental: " + valor(residuo.getRiscoAmbiental()), x, y);
        y += espaco;

        g.drawString("Valor Estimado: " + valor(residuo.getValorEstimado()) + " " + valor(residuo.getMoeda()), x, y);
        y += espaco;

        g.drawString("Local: " + valor(residuo.getCidade()) + " - " + valor(residuo.getEstado()) + " / " + valor(residuo.getPais()), x, y);
        y += espaco;

        g.drawString("Destino: " + valor(residuo.getDestino()), x, y);
        y += espaco;

        g.drawString("Status: " + valor(residuo.getStatus()), x, y);

        desenharSeloRisco(g, residuo);
    }

    private void desenharSeloRisco(Graphics2D g, ResiduoEletronico residuo) {
        int x = 620;
        int y = 160;
        int largura = 220;
        int altura = 180;

        g.setColor(new Color(245, 245, 245));
        g.fillRoundRect(x, y, largura, altura, 25, 25);

        g.setColor(new Color(0, 100, 70));
        g.drawRoundRect(x, y, largura, altura, 25, 25);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(new Color(0, 100, 70));
        g.drawString("Classificação", x + 42, y + 45);

        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString(valor(residuo.getRiscoAmbiental()), x + 55, y + 100);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.DARK_GRAY);
        g.drawString("Descarte adequado", x + 45, y + 140);
        g.drawString("recomendado", x + 70, y + 160);
    }

    private void desenharRodape(Graphics2D g) {
        g.setColor(new Color(0, 100, 70));
        g.fillRect(0, ALTURA - 55, LARGURA, 55);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Gerado automaticamente pelo EcoWaste Framework", 40, ALTURA - 22);
    }

    private String valor(Object valor) {
        if (valor == null) {
            return "Não informado";
        }

        return String.valueOf(valor);
    }
}