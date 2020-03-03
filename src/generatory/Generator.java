package generatory;

import języki.Język;

public abstract class Generator{
    Język język;
    String nazwa;

    public Generator(Język język){
        this.język = język;
    }

    public abstract String dajSłowo();

    public String nazwa(){
        return nazwa;
    }
}
