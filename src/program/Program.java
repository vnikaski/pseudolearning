package program;

import generatory.Generator;
import generatory.Generator2;
import języki.Język;
import języki.Język2;

import java.io.FileNotFoundException;

public class Program {
    public void test(String nazwaPliku, Język j) throws FileNotFoundException {
        System.out.print("==> Uczenie z pliku " + nazwaPliku + ", języka " + j.nazwa());
        j.uczSięzPliku(nazwaPliku);

        Generator g = new Generator2(j);
        System.out.println(" i generatora = " + g.nazwa() +  ": ");
        for(int i = 0; i<10; i++){
            System.out.println(i + ": " + g.dajSłowo());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Program o = new Program();
        o.test("pan-tadeusz.txt", new Język2());
        o.test("lokomotywa.txt", new Język2());
        o.test("oda-do-mlodosci.txt", new Język2());

    }
}
