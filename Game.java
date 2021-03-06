import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> pila;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        pila = new Stack<Room>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, biblioteca, cocina, habitacion, salon, ba�o, almacen, patio;
        // create the rooms
        entrada = new Room("entrada de la casa");
        biblioteca = new Room("biblioteca de la casa");
        cocina = new Room("cocina de la casa");
        habitacion = new Room("habitacion de la casa");
        salon = new Room("salon de la casa");
        ba�o = new Room("ba�o de la casa");
        almacen = new Room("almacen de la casa");
        patio = new Room ("patio de la casa");

        // initialise room exits
        entrada.setExit("south", biblioteca);
        biblioteca.setExit("north", entrada);
        biblioteca.setExit("east", ba�o);
        biblioteca.setExit("south", cocina);
        cocina.setExit("north", biblioteca);
        cocina.setExit("west", habitacion);
        cocina.setExit("east", salon);
        habitacion.setExit("east", cocina);
        salon.setExit("west", cocina);
        salon.setExit("north", ba�o);
        salon.setExit("northEast", patio);
        ba�o.setExit("west", biblioteca);
        ba�o.setExit("east", almacen);
        ba�o.setExit("south", salon);
        almacen.setExit("west", ba�o);

        //a�adimos objetos a las localizaciones
        entrada.addItem(new Item("caja", 10));
        biblioteca.addItem(new Item("libro", 1));
        biblioteca.addItem(new Item ("enciclopedia", 2));

        currentRoom = entrada;  // start game outside

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println("");
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look")){
            System.out.println(currentRoom.getLongDescription());
        }
        else if(commandWord.equals("eat")){
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if(commandWord.equals ("back")){
            goPreviousRoom();
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.printValidCommands();
    }

    /**
     * Metodo que vuelve a la habitacion anterior en la que hemos estado
     */
    private void goPreviousRoom()
    {
        if(!pila.empty())
        {
            currentRoom = pila.pop();
            printLocationInfo();
            System.out.println();
            
        }
       
        else
        {
            System.out.println("No hay forma de volver");
        }
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = null; 
        nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            pila.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }

    }

    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}
