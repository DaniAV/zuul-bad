import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap <String, Room> exits;
    private ArrayList<Item> item;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap();
        this.item = new ArrayList<Item>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        if(neighbor != null){
            exits.put(direction, neighbor);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String salidas = "Exits: ";
        if(exits.containsKey("north")){
            salidas += "north ";
        }
        if(exits.containsKey("east")){
            salidas += "east ";
        }
        if(exits.containsKey("south")){
            salidas += "south ";
        }
        if(exits.containsKey("west")){
            salidas += "west ";
        }
        if(exits.containsKey("southEast")){
            salidas += "southEastE ";
        }
        if(exits.containsKey("northWest")){
            salidas += "northWest ";
        }
        return salidas;   
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String mens = "";
        for(int i = 0; i<item.size();i++){
            mens += "\n-" + item.get(i).getDescription();
        }

        if(item.size() > 0){

            mens = ("Usted esta en " + this.description  + " con objetos: "
                + mens + "\n" + getExitString());
        }
        else{
            mens = ("Usted esta en " + this.description  + " esta localizacion no contiente objetos " 
                + "\n" + getExitString());
            
        }
        return mens;
    }

    /**
     * Metodo que permite añadir Items a una cierta localizacion
     */
    public void addItem(Item newItems)
    {
        item.add(newItems);
    }
}
