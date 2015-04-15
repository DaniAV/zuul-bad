
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;
    private float kg;

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, float kg)
    {
        // initialise instance variables
        this.description = description;
        this.kg = kg;
    }
    
    /**
     * Metodo que devuelve la descripcion del objeto Item.
     * @return Devuelve la descripcion del objeto.
     */
    
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Metodo que devuelve la informacion de cada item
     */
    public String toStringItem()
    {
        String info = "Item: " + description + " Peso: " + kg;
        return info;
    }
}
