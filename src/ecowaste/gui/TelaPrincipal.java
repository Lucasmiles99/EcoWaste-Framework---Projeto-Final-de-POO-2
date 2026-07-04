package ecowaste.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ecowaste.service.ResultadoDocumentosResiduo;
import ecowaste.model.ResiduoEletronico;
import ecowaste.framework.EcoWasteFramework;
import ecowaste.framework.EcoWasteFrameworkBuilder;

/**
 * Classe responsável pela interface gráfica principal do sistema EcoWaste Framework.
 *
 * <p>
 * Esta tela permite ao usuário realizar o gerenciamento de resíduos eletrônicos,
 * oferecendo funcionalidades de cadastro, busca, atualização e remoção de registros.
 * Além disso, exibe os resíduos cadastrados em uma tabela e apresenta um dashboard
 * com informações resumidas, como total de resíduos, peso total, quantidade de itens
 * com risco alto e número de categorias cadastradas.
 * </p>
 *
 * <p>
 * A classe também integra a interface gráfica com a geração automática de documentos
 * do resíduo eletrônico. Após o cadastro de um item, o sistema aciona o serviço
 * responsável por gerar imagens nos formatos PNG, PPM, PGM, PBM, canais RGB e o
 * arquivo XML contendo os dados do resíduo e os caminhos dos arquivos gerados.
 * </p>
 *
 * @author Lucas Miles
 */

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel labelTotalResiduos;
    private JLabel labelPesoTotal;
    private JLabel labelRiscoAlto;
    private JLabel labelCategorias;
    private JTextField campoId, campoNome, campoCategoria, campoPeso, campoRisco;
    private JTextField campoValor;
    private JTextField campoMoeda;
    private JTextField campoCidade;
    private JTextField campoEstado;
    private JTextField campoPais;
    private JTextField campoContinente;
    private JTextField campoDestino;
    private JTextField campoStatus;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private EcoWasteFramework framework = EcoWasteFrameworkBuilder
            .novo()
            .gerarDocumentosAutomaticamente(false)
            .build();
    
    public TelaPrincipal() {
        setTitle("EcoWaste Framework");
        setSize(950, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 247, 250));

        criarCabecalho();
        criarDashboard();
        criarFormulario();
        criarTabela();

        setVisible(true);
    }

    private void criarCabecalho() {
        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(20, 83, 45));
        cabecalho.setPreferredSize(new Dimension(950, 80));
        cabecalho.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("♻ EcoWaste Framework");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 25, 0, 0));

        JLabel subtitulo = new JLabel("Gerenciamento inteligente de resíduos eletrônicos");
        subtitulo.setForeground(new Color(220, 255, 230));
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setBorder(BorderFactory.createEmptyBorder(0, 28, 10, 0));

        JPanel textos = new JPanel(new GridLayout(2, 1));
        textos.setBackground(new Color(20, 83, 45));
        textos.add(titulo);
        textos.add(subtitulo);

        cabecalho.add(textos, BorderLayout.WEST);
        add(cabecalho, BorderLayout.NORTH);
    }
    
    private void criarDashboard() {

        JPanel painelDashboard = new JPanel(new GridLayout(1, 4, 15, 15));
        painelDashboard.setBackground(new Color(245, 247, 250));
        painelDashboard.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        labelTotalResiduos = new JLabel("0");
        labelPesoTotal = new JLabel("0.0 kg");
        labelRiscoAlto = new JLabel("0");
        labelCategorias = new JLabel("0");

        painelDashboard.add(criarCardDashboard("Total de Resíduos", labelTotalResiduos));
        painelDashboard.add(criarCardDashboard("Peso Total", labelPesoTotal));
        painelDashboard.add(criarCardDashboard("Risco Alto", labelRiscoAlto));
        painelDashboard.add(criarCardDashboard("Categorias", labelCategorias));

        add(painelDashboard, BorderLayout.SOUTH);
    }
    
    private JPanel criarCardDashboard(String titulo, JLabel valor) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelTitulo.setForeground(new Color(60, 60, 60));

        valor.setFont(new Font("Segoe UI", Font.BOLD, 26));
        valor.setForeground(new Color(20, 83, 45));
        valor.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(labelTitulo, BorderLayout.NORTH);
        card.add(valor, BorderLayout.CENTER);

        return card;
    }
    
    private void atualizarDashboard() {
        int total = framework.listarResiduos().size();
        double pesoTotal = 0.0;
        int riscoAlto = 0;
        java.util.Set<String> categorias = new java.util.HashSet<>();

        for (ResiduoEletronico residuo : framework.listarResiduos()) {
            pesoTotal += residuo.getPeso();

            if (residuo.getRiscoAmbiental().equalsIgnoreCase("Alto")) {
                riscoAlto++;
            }

            categorias.add(residuo.getCategoria());
        }

        labelTotalResiduos.setText(String.valueOf(total));
        labelPesoTotal.setText(String.format("%.2f kg", pesoTotal));
        labelRiscoAlto.setText(String.valueOf(riscoAlto));
        labelCategorias.setText(String.valueOf(categorias.size()));
    }

    private void criarFormulario() {
    	JPanel painel = new JPanel(new GridLayout(16, 2, 12, 12));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25),
                BorderFactory.createTitledBorder("Cadastro de Resíduo Eletrônico")
        ));

        campoId = criarCampo();
        campoNome = criarCampo();
        campoCategoria = criarCampo();
        campoPeso = criarCampo();
        campoRisco = criarCampo();
        campoValor = criarCampo();
        campoMoeda = criarCampo();
        campoCidade = criarCampo();
        campoEstado = criarCampo();
        campoPais = criarCampo();
        campoContinente = criarCampo();
        campoDestino = criarCampo();
        campoStatus = criarCampo();

        JButton botaoCadastrar = new JButton("Cadastrar Resíduo");
        JButton botaoBuscar = new JButton("Buscar");
        JButton botaoAtualizar = new JButton("Atualizar");
        JButton botaoRemover = new JButton("Remover");
        botaoCadastrar.setBackground(new Color(34, 197, 94));
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoCadastrar.setFocusPainted(false);

        botaoCadastrar.addActionListener(e -> cadastrarResiduo());
        botaoBuscar.addActionListener(e -> buscarResiduo());

        botaoAtualizar.addActionListener(e -> atualizarResiduo());

        botaoRemover.addActionListener(e -> removerResiduo());

        painel.add(criarLabel("ID:"));
        painel.add(campoId);

        painel.add(criarLabel("Nome:"));
        painel.add(campoNome);

        painel.add(criarLabel("Categoria:"));
        painel.add(campoCategoria);

        painel.add(criarLabel("Peso:"));
        painel.add(campoPeso);

        painel.add(criarLabel("Risco Ambiental:"));
        painel.add(campoRisco);
        
        painel.add(criarLabel("Valor Estimado:"));
        painel.add(campoValor);

        painel.add(criarLabel("Moeda:"));
        painel.add(campoMoeda);

        painel.add(criarLabel("Cidade:"));
        painel.add(campoCidade);

        painel.add(criarLabel("Estado:"));
        painel.add(campoEstado);

        painel.add(criarLabel("País:"));
        painel.add(campoPais);

        painel.add(criarLabel("Continente:"));
        painel.add(campoContinente);

        painel.add(criarLabel("Destino:"));
        painel.add(campoDestino);

        painel.add(criarLabel("Status:"));
        painel.add(campoStatus);

        painel.add(botaoCadastrar);
        painel.add(botaoBuscar);

        painel.add(botaoAtualizar);
        painel.add(botaoRemover);

        add(painel, BorderLayout.WEST);
    }

    private void criarTabela() {
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Categoria");
        modeloTabela.addColumn("Peso");
        modeloTabela.addColumn("Risco");
        modeloTabela.addColumn("Valor");
        modeloTabela.addColumn("Moeda");
        modeloTabela.addColumn("Cidade");
        modeloTabela.addColumn("Estado");
        modeloTabela.addColumn("País");
        modeloTabela.addColumn("Continente");
        modeloTabela.addColumn("Destino");
        modeloTabela.addColumn("Status");

        tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(new Color(15, 118, 110));
        tabela.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(scroll, BorderLayout.CENTER);
    }

    private JTextField criarCampo() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return campo;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    private void cadastrarResiduo() {
        try {
            Long id = Long.parseLong(campoId.getText());
            String nome = campoNome.getText();
            String categoria = campoCategoria.getText();
            double peso = Double.parseDouble(campoPeso.getText());
            String risco = campoRisco.getText();
            double valorEstimado = Double.parseDouble(campoValor.getText());
            String moeda = campoMoeda.getText();
            String cidade = campoCidade.getText();
            String estado = campoEstado.getText();
            String pais = campoPais.getText();
            String continente = campoContinente.getText();
            String destino = campoDestino.getText();
            String status = campoStatus.getText();

            ResiduoEletronico residuo = new ResiduoEletronico(
                    id,
                    nome,
                    categoria,
                    peso,
                    risco,
                    valorEstimado,
                    moeda,
                    cidade,
                    estado,
                    pais,
                    continente,
                    destino,
                    status
            );

            framework.cadastrarResiduo(residuo);

            modeloTabela.addRow(new Object[] {
                    residuo.getId(),
                    residuo.getNome(),
                    residuo.getCategoria(),
                    residuo.getPeso(),
                    residuo.getRiscoAmbiental(),
                    residuo.getValorEstimado(),
                    residuo.getMoeda(),
                    residuo.getCidade(),
                    residuo.getEstado(),
                    residuo.getPais(),
                    residuo.getContinente(),
                    residuo.getDestino(),
                    residuo.getStatus()
            });

            limparCampos();

            atualizarDashboard();

            gerarDocumentosDoResiduo(residuo);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao cadastrar resíduo: " + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoCategoria.setText("");
        campoPeso.setText("");
        campoRisco.setText("");
        campoValor.setText("");
        campoMoeda.setText("");
        campoCidade.setText("");
        campoEstado.setText("");
        campoPais.setText("");
        campoContinente.setText("");
        campoDestino.setText("");
        campoStatus.setText("");
    }
    
    private void buscarResiduo() {

        try {

            Long id = Long.parseLong(campoId.getText());

            ResiduoEletronico residuo = framework.buscarResiduoPorId(id);

            if (residuo != null) {

                campoNome.setText(residuo.getNome());
                campoCategoria.setText(residuo.getCategoria());
                campoPeso.setText(String.valueOf(residuo.getPeso()));
                campoRisco.setText(residuo.getRiscoAmbiental());
                campoValor.setText(String.valueOf(residuo.getValorEstimado()));
                campoMoeda.setText(residuo.getMoeda());
                campoCidade.setText(residuo.getCidade());
                campoEstado.setText(residuo.getEstado());
                campoPais.setText(residuo.getPais());
                campoContinente.setText(residuo.getContinente());
                campoDestino.setText(residuo.getDestino());
                campoStatus.setText(residuo.getStatus());

                JOptionPane.showMessageDialog(this,
                        "Resíduo encontrado!");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Resíduo não encontrado.");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Informe um ID válido.");

        }

    }
    
    private void atualizarResiduo() {

        try {

            Long id = Long.parseLong(campoId.getText());

            ResiduoEletronico residuo = new ResiduoEletronico(
                    id,
                    campoNome.getText(),
                    campoCategoria.getText(),
                    Double.parseDouble(campoPeso.getText()),
                    campoRisco.getText(),
                    Double.parseDouble(campoValor.getText()),
                    campoMoeda.getText(),
                    campoCidade.getText(),
                    campoEstado.getText(),
                    campoPais.getText(),
                    campoContinente.getText(),
                    campoDestino.getText(),
                    campoStatus.getText()
            );

            framework.atualizarResiduo(residuo);

            atualizarTabela();
            
            atualizarDashboard();

            JOptionPane.showMessageDialog(this,
                    "Resíduo atualizado com sucesso!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Erro ao atualizar.");

        }

    }
    
    private void removerResiduo() {

        try {

        	Long id = Long.parseLong(campoId.getText());

        	ResiduoEletronico residuo = framework.buscarResiduoPorId(id);

        	if (residuo != null) {
        	    framework.removerResiduo(id);

        	    atualizarTabela();

        	    limparCampos();

        	    atualizarDashboard();

        	    JOptionPane.showMessageDialog(
        	            this,
        	            "Resíduo removido com sucesso!",
        	            "EcoWaste Framework",
        	            JOptionPane.INFORMATION_MESSAGE
        	    );
        	} else {
        	    JOptionPane.showMessageDialog(
        	            this,
        	            "Nenhum resíduo encontrado com esse ID.",
        	            "Aviso",
        	            JOptionPane.WARNING_MESSAGE
        	    );
        	}

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Erro ao remover.");

        }

    }
    
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);

        for (ResiduoEletronico residuo : framework.listarResiduos()) {
            modeloTabela.addRow(new Object[] {
                    residuo.getId(),
                    residuo.getNome(),
                    residuo.getCategoria(),
                    residuo.getPeso(),
                    residuo.getRiscoAmbiental(),
                    residuo.getValorEstimado(),
                    residuo.getMoeda(),
                    residuo.getCidade(),
                    residuo.getEstado(),
                    residuo.getPais(),
                    residuo.getContinente(),
                    residuo.getDestino(),
                    residuo.getStatus()
            });
        }
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
    
    private void gerarDocumentosDoResiduo(ResiduoEletronico residuo) {
        try {
            ResultadoDocumentosResiduo resultado = framework.gerarDocumentos(residuo);

            JOptionPane.showMessageDialog(
                    this,
                    "Resíduo cadastrado e documentos gerados com sucesso!\n\n"
                            + "XML: " + resultado.getArquivoXml().getName(),
                    "EcoWaste Framework",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "O resíduo foi cadastrado, mas ocorreu erro ao gerar os documentos:\n"
                            + e.getMessage(),
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}