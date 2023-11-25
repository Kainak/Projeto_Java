package model.entities;
import java.io.Serializable;
import java.util.Objects;

public class Produtor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer IDprodutor;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String telefone2;
    private String cnpj;
    private String razaoSocial;
    private boolean producaoPropria;

    public Produtor() {

    }

    public Produtor(Integer escolha) {
    }

//    public Produtor(Integer escolha, Object o) {
//    }

    public Integer getIDprodutor() {
        return IDprodutor;
    }

    public void setIDprodutor(Integer IDprodutor) {
        this.IDprodutor = IDprodutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public boolean isProducaoPropria() {
        return producaoPropria;
    }

    public void setProducaoPropria(boolean producaoPropria) {
        this.producaoPropria = producaoPropria;
    }

    public Produtor(Integer IDprodutor, String nome, String cpf, String email, String telefone, String telefone2, String cnpj, String razaoSocial, boolean producaoPropria) {
        this.IDprodutor = IDprodutor;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.telefone2 = telefone2;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.producaoPropria = producaoPropria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produtor produtor = (Produtor) obj;
        return Objects.equals(IDprodutor, produtor.IDprodutor) &&
                Objects.equals(nome, produtor.nome) &&
                Objects.equals(cpf, produtor.cpf) &&
                Objects.equals(email, produtor.email) &&
                Objects.equals(telefone, produtor.telefone) &&
                Objects.equals(telefone2, produtor.telefone2) &&
                Objects.equals(cnpj, produtor.cnpj) &&
                Objects.equals(razaoSocial, produtor.razaoSocial) &&
                producaoPropria == produtor.producaoPropria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDprodutor, nome, cpf, email, telefone, telefone2, cnpj, razaoSocial, producaoPropria);
    }


    @Override
    public String toString() {
        return "Produtor{" +
                "IDprodutor=" + IDprodutor +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", producaoPropria=" + producaoPropria +
                '}';
    }

}
