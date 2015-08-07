package classesDados;

import java.io.Serializable;
import java.util.ArrayList;

import enumerados.TipoEstadoCivil;
import enumerados.TipoGenero;
import java.util.Date;

public class Pessoa implements Serializable {

    private int idPessoa;
    private String nome_completo;
    private String[] nome;
    private String nomeProprio;
    private String apelido;
    private Date data_de_nascimento;
    private Morada local_nascimento;
    private Morada morada;
    private TipoGenero genero;
    private String profissao;
    private Imagem foto;
    private TipoEstadoCivil estadoCivil;

    private ArrayList<Relacao> relacoes;
    private ArrayList<Evento> eventos;

    public Pessoa(int idPessoa, String nome_completo, Date data_de_nascimento,
            Morada local_nascimento, TipoGenero genero, TipoEstadoCivil estado_civil, String profissao,
            Morada morada) {
        this.idPessoa = idPessoa;
        this.nome_completo = nome_completo;
        System.out.println("nome completo do const: " + nome_completo);
        System.out.println("nome completo do this: " + this.nome_completo);
        String[] nomeTemp = this.nome_completo.split(" ");
        this.nomeProprio = nomeTemp[0];
        System.out.println("nome proprio: " + nomeProprio);
        this.apelido = nomeTemp[nomeTemp.length - 1];
        System.out.println("apelido : " + apelido);
        this.data_de_nascimento = data_de_nascimento;
        this.local_nascimento = local_nascimento;
        this.genero = genero;
        this.estadoCivil = estado_civil;
        this.profissao = profissao;
        this.morada = morada;
        relacoes = new ArrayList<Relacao>();
        eventos = new ArrayList<Evento>();
    }

    public void setFoto(Imagem foto) {
        this.foto = foto;
    }

    public Imagem getFoto() {
        return foto;
    }

    public void novaRelacao(Relacao r) {
        relacoes.add(r);
    }

    public boolean eliminaRelacao(Relacao r) {
        for (Relacao rel : relacoes) {
            if (rel == r) {
                relacoes.remove(rel);
                return true;
            }
        }
        System.out.println("Essa relação não existe");
        return false;
    }

    public Relacao existeRelacaoPessoa(Pessoa p) {
        for (Relacao rel : relacoes) {
            if (rel.getFamiliar_nivel1().equals(p) || rel.getFamiliar_nivel2().equals(p)) {
                System.out.println("Essa relação existe: \n");
                return rel;
            }
        }
        System.out.println("Essa relação não existe");
        return null;
    }

    public Relacao existeRelacaoPessoa_Pessoa(Pessoa p, Pessoa q) {
        for (Relacao rel : relacoes) {
            if ((rel.getFamiliar_nivel1().equals(p) && rel.getFamiliar_nivel2().equals(q))
                    || (rel.getFamiliar_nivel1().equals(q) && rel.getFamiliar_nivel2().equals(p))) {
                return rel;
            }
        }
        return null;
    }

    public Relacao existeRelacaoPaciente(Paciente p) {
        for (Relacao rel : relacoes) {
            if (rel.getPaciente() == p) {
                System.out.println("Essa relação existe: \n");
                return rel;
            }
        }
        System.out.println("Essa relação não existe");
        return null;
    }

    public String imprimeRelacoes() {
        for (Relacao r : relacoes) {
            return r.toString() + ";\n";
        }
        System.out.println("Não há relações entre esta pessoa");
        return null;
    }

    public void novoEvento(Evento e) {
        eventos.add(e);
    }

    public boolean eliminaEvento(Evento e) {
        for (Evento ev : eventos) {
            if (ev == e) {
                eventos.remove(ev);
                return true;
            }
        }
        System.out.println("Esse evento não existe");
        return false;
    }

    public Evento existeEvento(Evento e) {
        for (Evento ev : eventos) {
            if (ev == e) {
                System.out.println("Esse evento existe: \n");
                return ev;
            }
        }
        System.out.println("Esse evento não existe");
        return null;
    }

    public String imprimeeventos() {
        for (Evento ev : eventos) {
            return ev.toString() + ";\n";
        }
        System.out.println("n�o h� eventos para esta pessoa");
        return null;
    }

    //GETTERS:
    public int getId() {
        return idPessoa;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public String[] getNome() {
        return nome;
    }

    public String getNomeProprio() {
        return nomeProprio;
    }

    public String getApelido() {
        return apelido;
    }

    public Date getData_de_nascimento() {
        return data_de_nascimento;
    }

    public Morada getLocal_nascimento() {
        return local_nascimento;
    }

    public Morada getMorada() {
        return morada;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public TipoEstadoCivil getEstadoCivil() {
        return estadoCivil;
    }
    
    public String getProfissao() {
        return profissao;
    }

    public ArrayList<Relacao> getRelacoes() {
        return relacoes;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
    
    

    //SETTERS:
    public void setNomeCompleto(String nome) {
        this.nome_completo = nome;
    }

    public void setApelido(String nome) {
        this.apelido = nome;
    }

    public void setNomeProprio(String nome) {
        this.nomeProprio = nome;
    }

    public void setMorada(Morada m) {
        this.morada = m;
    }

    public void setData_de_nascimento(Date data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public void setEstadoCivil(TipoEstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    
    public void setLocal_nascimento(Morada local_nascimento) {
        this.local_nascimento = local_nascimento;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String toString() {
        return nomeProprio + " " + apelido
                + " data de nascimento: ( " + data_de_nascimento.getDay() + "/" + data_de_nascimento.getMonth() + "/" + data_de_nascimento.getYear()
                + " prof. " + profissao;
    }
}
