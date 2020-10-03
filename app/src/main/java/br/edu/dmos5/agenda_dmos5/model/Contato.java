package br.edu.dmos5.agenda_dmos5.model;

public class Contato {
    private String nome;
    private String tel_cel;
    private String tel_fix;

    public Contato(String nome, String tel_cel, String tel_fix) {
        this.nome = nome;
        this.tel_cel = tel_cel;
        this.tel_fix = tel_fix;
    }

    public Contato(String nome, String tel) {
        this.nome = nome;
        cadastraFoneCerto(tel);
    }

    private void cadastraFoneCerto(String tel) {
        if(tel.length() == 10){
            this.setTel_fix(tel);
        }else{
            this.setTel_cel(tel);
        }
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel_cel() {
        return tel_cel;
    }

    public void setTel_cel(String tel_cel) {
        this.tel_cel = tel_cel;
    }

    public String getTel_fix() {
        return tel_fix;
    }

    public void setTel_fix(String tel_fix) {
        this.tel_fix = tel_fix;
    }
}
