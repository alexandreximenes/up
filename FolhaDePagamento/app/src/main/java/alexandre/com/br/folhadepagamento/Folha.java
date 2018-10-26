package alexandre.com.br.folhadepagamento;

public class Folha {
    private String nome;
    private int horasTrab;
    private float valorHora;
    private float salBruto;
    private float salLiq;
    private float ir;
    private float inss;
    private float fgts;

    public Folha(String nome, int horasTrab, float valorHora) {
        this.nome = nome;
        this.horasTrab = horasTrab;
        this.valorHora = valorHora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrab() {
        return horasTrab;
    }

    public void setHorasTrab(int horasTrab) {
        this.horasTrab = horasTrab;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public float getSalBruto() {
        return salBruto = (float) horasTrab * valorHora;
    }

    public void setSalBruto(float salBruto) {
        this.salBruto = salBruto;
    }

    public float getSalLiq() {
        return salBruto - getIr() - getInss();
    }

    public void setSalLiq(float salLiq) {
        this.salLiq = salLiq;
    }

    public float getIr() {
        if(salBruto >= 1372.82f && salBruto <= 2743.25f){
            ir = salBruto * 0.15f;
        }else if(salBruto > 2743.25f){
            ir = salBruto * 0.275f;
        }
        return ir;
    }

    public void setIr(float ir) {
        this.ir = ir;
    }

    public float getInss() {
        if(salBruto <= 868.29f){
            inss = salBruto * 0.08f;
        }else if(salBruto >= 868.30f && salBruto <= 1447.14f){
            inss = salBruto * 0.09f;
        }else if(salBruto >= 1447.15f && salBruto <= 2894.29f){
            inss = salBruto * 0.11f;
        }else if(salBruto > 2894.29f){
            inss = 318.37f;
        }
        return inss;
    }

    public void setInss(float inss) {
        this.inss = inss;
    }

    public float getFgts() {
        return salBruto * 0.08f;
    }

    public void setFgts(float fgts) {
        this.fgts = fgts;
    }

    @Override
    public String toString() {
        return "Folha{" +
                "nome='" + nome + '\'' +
                ", tvHorasTrab=" + horasTrab +
                ", tvValorHora=" + valorHora +
                ", tvSalBruto=" + salBruto +
                ", tvSalLiq=" + salLiq +
                ", tvIR=" + ir +
                ", tvINSS=" + inss +
                ", tvFGTS=" + fgts +
                '}';
    }
}
