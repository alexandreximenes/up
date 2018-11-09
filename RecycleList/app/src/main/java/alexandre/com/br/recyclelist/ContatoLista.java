package alexandre.com.br.recyclelist;

import java.util.ArrayList;

public class ContatoLista {

    private static ArrayList<Contato> listaContatos = new ArrayList<>();

    public static void add(Contato c){
        listaContatos.add(c);
    }

    public static Contato remove(int index){
        return listaContatos.remove(index);
    }

    public static Contato remove(String nome){
        int index = listaContatos.indexOf(nome);
        if(index>0){
            return listaContatos.remove(index);
        }
        return null;
    }
    public static Contato get(int index){
        return listaContatos.get(index);
    }
    public static ArrayList<Contato> all() {
        return listaContatos;
    }

    public static void generateList(){
        listaContatos.add(new Contato("Alexandre", "41 99999", "xyymenes@gmail.com", "Rua A"));
        listaContatos.add(new Contato("Amanda", "41 8888", "Amanda@gmail.com", "Rua B"));
        listaContatos.add(new Contato("Roberto", "41 77777", "Roberto@gmail.com", "Rua C"));
        listaContatos.add(new Contato("Maria", "41 666666", "Maria@gmail.com", "Rua D"));
        listaContatos.add(new Contato("Regina", "41 555555", "Regina@gmail.com", "Rua E"));
        listaContatos.add(new Contato("Zezinho", "41 444444", "Zezinho@gmail.com", "Rua F"));
        listaContatos.add(new Contato("Jose", "41 333333", "Jose@gmail.com", "Rua G"));
        listaContatos.add(new Contato("Tiago", "41 222222", "Tiago@gmail.com", "Rua H"));
        listaContatos.add(new Contato("Arthur", "41 111111", "Arthur@gmail.com", "Rua I"));
        listaContatos.add(new Contato("Mateus", "41 0000000", "Mateus@gmail.com", "Rua J"));
    }
}
