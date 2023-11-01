package model.entities;

import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

public class Documento {
    public Documento() {

    }

    @Override
    public String toString() {
        return "Documento{" +
                "IDdocumento=" + IDdocumentos +
                ", titulo='" + titulo + '\'' +
                ", data=" + data +
                ", data_venc=" + data_venc +
                ", documento=" + documento +
                ", produtor=" + produtor +
                '}';
    }

    private Integer IDdocumentos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento that = (Documento) o;
        return Objects.equals(IDdocumentos, that.IDdocumentos) && Objects.equals(titulo, that.titulo) && Objects.equals(data, that.data) && Objects.equals(data_venc, that.data_venc) && Objects.equals(documento, that.documento) && Objects.equals(produtor, that.produtor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDdocumentos, titulo, data, data_venc, documento, produtor);
    }

    public Integer getIDdocumentos() {
        return IDdocumentos;
    }

    public void setIDdocumentos(Integer IDdocumentos) {
        this.IDdocumentos = IDdocumentos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData_venc() {
        return data_venc;
    }

    public void setData_venc(Date data_venc) {
        this.data_venc = data_venc;
    }

    public InputStream getDocumento() {
        return documento;
    }

    public void setDocumento(InputStream documento) {
        this.documento = documento;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    public Documento(Integer IDdocumentos, String titulo, Date data, Date data_venc, InputStream documento, Produtor produtor) {
        this.IDdocumentos = IDdocumentos;
        this.titulo = titulo;
        this.data = data;
        this.data_venc = data_venc;
        this.documento = documento;
        this.produtor = produtor;
    }

    private String titulo;

    private Date data;

    private Date data_venc;

    private InputStream documento;

    private Produtor produtor;

    public Produtor getProdutor() {
        return produtor;
    }
}
