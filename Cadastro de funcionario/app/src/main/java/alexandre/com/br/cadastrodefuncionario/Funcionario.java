package alexandre.com.br.cadastrodefuncionario;

public class Funcionario {
    private String nome;
    private int horasTrab;
    private float valorHora;
    private String cargo;
    private float abono;
    private float salBruto;
    private float salLiq;

    public Funcionario(String nome, String cargo, int horasTrab, float valorHora) {
        this.nome = nome;
//        if(!cargo.equals("Programador") || !cargo.equals("Gerente") || !cargo.equals("Analista")){
//            throw new RuntimeException("Cargo Invalido!");
//        }
        this.cargo = cargo;
        this.horasTrab = horasTrab;
        this.valorHora = valorHora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalBruto() {
        return valorHora * horasTrab;
    }

    public void setSalBruto(float salBruto) {
        this.salBruto = salBruto;
    }

    public void setAbono(float abono) {
        this.abono = abono;
    }

    public int getHorasTrab() {
        return horasTrab;
    }

    public void setHorasTrab(int horasTrab) {
        this.horasTrab = horasTrab;
    }

    public float getSalLiq() {
        return getSalBruto() + getAbono();
    }

    public void setSalLiq(float salLiq) {
        this.salLiq = salLiq;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public float getAbono() {
        float abono = 0;
        if(cargo.equals("Programador")){
                abono = (float) (getSalBruto() * 0.08);
        }else if(cargo.equals("Analista")){
            abono = (float) (getSalBruto() * 0.15);
        }else if(cargo.equals("Gerente")){
            abono = (float) (getSalBruto() * 0.25);
        }
        return abono;
    }

}
