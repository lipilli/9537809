package DatabaseProject.DatabaseContent;

import java.util.ArrayList;
import java.util.HashMap;
/**Class that stores the medals of an athlete.*/
public class Medals {
    /**Stores medals.*/
    private HashMap<MedalType, ArrayList<Event>> medalsStore = new HashMap<MedalType, ArrayList<Event>>();

    public enum MedalType{
        GOLD,
        SILVER,
        BRONZE
    }

    /**Create GOLD, SILVER and BRONZE entries for medalsStore*/
    public Medals(){
        this.medalsStore.put(MedalType.GOLD, new ArrayList<Event>());
        this.medalsStore.put(MedalType.SILVER, new ArrayList<Event>());
        this.medalsStore.put(MedalType.BRONZE, new ArrayList<Event>());
    }



    public  HashMap<MedalType, ArrayList<Event>> getMedalsStore(){
        return this.medalsStore;
    }
    public ArrayList<Event> getGold(){return this.medalsStore.get(MedalType.GOLD);};
    public ArrayList<Event> getSilver(){return this.medalsStore.get(MedalType.SILVER);};
    public ArrayList<Event> getBronze(){return this.medalsStore.get(MedalType.BRONZE);};

    /**Adding a medal to medalStore
     * @param event event where medal was won
     * @param color MedalType
     */
    public void addMedal(MedalType color, Event event){
        if (color == MedalType.GOLD){ //TODO could be converted to enum
            this.medalsStore.get(MedalType.GOLD).add(event);
        }else if(color == MedalType.SILVER){
            this.medalsStore.get(MedalType.SILVER).add(event);
        }else if(color == MedalType.BRONZE){
            this.medalsStore.get(MedalType.BRONZE).add(event);
        } //TODO: Some error handling for when I enter the wrong thing
    }

    /**Extract information from medalUpdate
     *@param medalsUpdate medal containing one or no medalEvents
     * @return one-elemented hashmap with medal color and event or null if medalUpdate contains no medals  */
    public static MedalsUpdate getUpdate(Medals medalsUpdate){
        // check if gold medals in this have the first gold medal in update
        MedalType medalType;
        Event medalEvent; //

        try {
            medalType = MedalType.GOLD;
            medalEvent = medalsUpdate.getGold().get(0);
        }catch (IndexOutOfBoundsException e){
            try{
                medalType = MedalType.SILVER;
                medalEvent = medalsUpdate.getSilver().get(0);
            }catch (IndexOutOfBoundsException e2){
                try{
                    medalType = MedalType.BRONZE;
                    medalEvent = medalsUpdate.getBronze().get(0);
                }catch (IndexOutOfBoundsException e3){
                    medalType = MedalType.GOLD;
                    medalEvent = null;
                }
            }
        }

        if(medalEvent !=null){
            return new MedalsUpdate(medalType, medalEvent);
        }else{
            return null;
        }

    }
}
