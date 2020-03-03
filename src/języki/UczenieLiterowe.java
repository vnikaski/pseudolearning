package języki;

import java.io.FileNotFoundException;
import java.util.Map;

public interface UczenieLiterowe {
    public void przygotujSłownikUL();
    public void uczSię(String słowo);
    public void uczSięzPliku(String nazwa) throws FileNotFoundException;
}
