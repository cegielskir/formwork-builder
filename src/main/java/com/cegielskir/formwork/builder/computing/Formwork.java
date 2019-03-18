package com.cegielskir.formwork.builder.computing;

import com.cegielskir.formwork.builder.entity.GirderSet;
import com.cegielskir.formwork.builder.entity.Room;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.min;


public class Formwork {

    //configurable variables
    //TODO user settings
    private float upperExtraDistance = 0.30f;
    private float distance = 2.0f; //2.0 by def
    private float overlapDistance = 0.20f;
    private float oneUpperGirderDistance = 0.15f;
    private int accuracy = 2;


    private int min = 1000;
    private HashMap<Integer, Set<List<Integer>>> combinations;
    private List<Room> rooms;
    private HashMap<Float, Integer> girders;
    private int maxLeft = -1;
    private int maxLeftValue = -1;
    private boolean newFormwork = true;
    private HashMap<Integer, FormworkedRoom> finalFormwork = new HashMap<>();
    private StringBuffer resultString;
    private long buildTime;
    private long get3BestTime;
    private long areEnoughGirdersTime;
    private long getCombinationsTime;
    private long sortingTime;


    public Formwork() {
        this.rooms = new ArrayList<>();
        this.girders = new HashMap<>();
        this.combinations = new HashMap<>();
        this.resultString = new StringBuffer("");
    }

    private void initialize(){
        this.buildTime = 0;
        this.get3BestTime = 0;
        this.areEnoughGirdersTime = 0;
        this.getCombinationsTime = 0;
        this.sortingTime = 0;
        this.combinations = new HashMap<>();
    }


    // getters and setters

    public String getResultString() {
        return resultString.toString();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getSingleRoom(int index) {
        return this.rooms.get(index);
    }

    public HashMap<Float, Integer> getGirders() {
        return girders;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getOverlapDistance() {
        return overlapDistance;
    }

    public void setOverlapDistance(float overlapDistance) {
        this.overlapDistance = overlapDistance;
    }

    public int getMaxLeft() {
        return maxLeft;
    }

    public void setMaxLeft(int maxLeft) {
        this.maxLeft = maxLeft;
    }

    public int getMaxLeftValue() {
        return maxLeftValue;
    }

    public void setMaxLeftValue(int maxLeftValue) {
        this.maxLeftValue = maxLeftValue;
    }

    public float getOneUpperGirderDistance() {
        return oneUpperGirderDistance;
    }

    public void setOneUpperGirderDistance(float oneUpperGirderDistance) {
        this.oneUpperGirderDistance = oneUpperGirderDistance;
    }

    public void addGirderSets(List<GirderSet> girderSets){
        for(GirderSet girderSet: girderSets){
            this.girders.put(girderSet.getLen(), girderSet.getQuantity());
        }
    }

    public void addRooms(List<Room> rooms){
        this.rooms.addAll(rooms);
    }

    public HashMap<Integer, FormworkedRoom> getFinalFormwork() {
        return finalFormwork;
    }

    public float getUpperExtraDistance() {
        return upperExtraDistance;
    }

    public void setUpperExtraDistance(float upperExtraDistance) {
        this.upperExtraDistance = upperExtraDistance;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    // adders and removers

    public void addRoom(float len, float wid) {
        Room room = new Room(len, wid);
        this.rooms.add(room);
    }

    public void addGirders(float len, int n) {
        int number = this.girders.getOrDefault(len, 0);
        number += n;
        this.girders.put(len, number);
    }

    public void removeGirders(float len, int n) {
        int number = this.girders.get(len);
        number = number - n;
        this.girders.put(len, number);
    }



    // main recursive method
    public HashMap<Float, Integer> build(HashMap<Float, Integer> girders, boolean isMain,
                                         int indexOfRoom, int rows, float previousDistance, boolean lenChanged,
                                         boolean newFormwork) {
        if(newFormwork) initialize();
        long startTime = System.nanoTime();


        if (indexOfRoom >= this.rooms.size()) {
            int leftValue = girdersLeftValue(girders);
            int left = girdersLeft(new ArrayList<>(girders.values()));
            if ( ( left > this.maxLeft) || (left == this.maxLeft && leftValue > this.maxLeftValue) ) {
                this.maxLeft = left;
                this.maxLeftValue = leftValue;
                return girders;
            } else {
                return null;
            }
        }
        int detailIndex = 0;
        int pos = 0;
        Room room;
        List<List<Float>> possibilities = new ArrayList<List<Float>>();
        List<GirdersDetails> details = new ArrayList<>();
        String name1, name2;
        HashMap<Float, Integer> updatedGirders = null, newGirders;
        //TODO room class method to change len with width
        room = new Room(this.rooms.get(indexOfRoom).getLen(), this.rooms.get(indexOfRoom).getWid());
        if(lenChanged && !isMain)  room = new Room(room.getWid(), room.getLen());

        int i = 0;
        while (i < 2 && updatedGirders == null) {
            detailIndex = 0;
            this.buildTime = this.buildTime + System.nanoTime() - startTime;
            details = buildGirders(isMain, isMain ? room.getWid() : room.getLen());
            startTime = System.nanoTime();
            while (detailIndex < details.size() && updatedGirders == null){
                GirdersDetails detail = details.get(detailIndex);
                boolean info = false;
                if (indexOfRoom + 1 == this.rooms.size() && !isMain) info = true;
                this.buildTime = this.buildTime + System.nanoTime() - startTime;
                possibilities = get3Best(girders, detail.getNumberOfRows(),
                        isMain ? room.getLen() : room.getWid(), rows - 1, isMain,
                        previousDistance, info);
                startTime = System.nanoTime();
                pos = 0;
                while (pos < possibilities.size() && updatedGirders == null) {
                    newGirders = new HashMap<>(girders);
                    for (Float gir : possibilities.get(pos)) {
                        newGirders.put(gir, newGirders.get(gir) - detail.getNumberOfRows());
                    }
                    this.buildTime = this.buildTime + System.nanoTime() - startTime;
                    updatedGirders = build(newGirders, !isMain, isMain ? indexOfRoom : indexOfRoom + 1,
                            detail.getNumberOfRows(), detail.getDistanceBetweenRows(),
                            i == 1, false);
                    startTime = System.nanoTime();
                    if (updatedGirders == null) pos++;
                }
                if(updatedGirders == null) detailIndex++;

            }
            if (isMain && updatedGirders == null) {
                room = new Room(room.getWid(), room.getLen());
                i++;
            } else i += 4;
        }
        if (updatedGirders == null) return null;

        this.buildTime = this.buildTime + System.nanoTime() - startTime;
        if(indexOfRoom == rooms.size() - 1 && !isMain) {
            this.resultString = new StringBuffer("");
            this.finalFormwork = new HashMap<>();
        }
        setFormworkedRoom(isMain, indexOfRoom, room, details.get(detailIndex).getNumberOfRows(),
                possibilities.get(pos), (int) (details.get(detailIndex).getDistanceBetweenRows() * 100));

        buildResultString(room, details.get(detailIndex).getNumberOfRows(),
                details.get(detailIndex).getDistanceBetweenRows(), possibilities.get(pos), isMain);

        return updatedGirders;
    }


    // helpful methods used for outsourcing tasks from the main method

    private List<GirdersDetails> buildGirders(boolean isMain, float roomLen) {
        //more distances can be added
        List<Float> distances = new ArrayList<>();
        List<GirdersDetails> details = new ArrayList<>();
        if (isMain) {
            distances.add(this.distance);
        } else distances.add(0.515f);
        for (Float distance : distances) {
            int girdersRows = calculateNumberOfRows(roomLen, distance, isMain);
            details.add(new GirdersDetails(girdersRows, calculateDistance(roomLen, girdersRows, isMain)));
        }


        return details;
    }

    private List<List<Float>> get3Best(HashMap<Float, Integer> girders, int n, float len,
                                       int min, boolean isMain, float previousDistance,
                                       boolean info) {
        long startTime = System.nanoTime();
        HashMap<GirderValue, List<Float>> girdersMap = new HashMap<>();
        List<List<Float>> finalList = new ArrayList<>();
        List<Float> girdersKey = new ArrayList<>(this.girders.keySet());
        int j = 0;
        if (!isMain) j = min;
        else j = 2;
        int maxComb;
        if(len < 5.0) maxComb = 3;
        else if(len > 8.0) maxComb = 5;
        else maxComb = 4;
        for (int i = j; i < maxComb + 1; i++) { // i - number of girders in combination i = 2 => {4, 5}, i = 3 => {1, 4, 3}
            this.get3BestTime = this.get3BestTime + System.nanoTime() - startTime;
            Set<List<Integer>> comb = getCombinations(girdersKey.size(), i);
            startTime = System.nanoTime();
            for (List<Integer> positions : comb) {
                HashMap<Float, Integer> copyOfGirders = new HashMap<>(girders);
                List<Float> newOption = new ArrayList<>();
                float sum = 0;
                this.get3BestTime = this.get3BestTime + System.nanoTime() - startTime;
                if (!isMain && !isUpperRowValid(girdersKey, positions, min, previousDistance)) continue;
                startTime = System.nanoTime();
                float max = 0;
                for (Integer pos : positions) {
                    float girLen = girdersKey.get(pos);
                    if (girLen > len) continue;
                    if (girLen > max) max = girLen;
                    if (i == 1 && girLen > len) continue;
                    //if there's only one girder needed in each upper row, then girders can be set like
                    // |---------  |
                    // |  ---------|
                    // |---------  |
                    // |  ---------|
                    // and that is why something is added to sum
                    if (i == 1) sum = sum + this.oneUpperGirderDistance;
                    newOption.add(girLen);
                    sum = sum + girLen;
                    int numberOfGirdersLeft = copyOfGirders.get(girLen);
                    numberOfGirdersLeft -= n;
                    copyOfGirders.put(girLen, numberOfGirdersLeft);
                }
                sum = sum - len - this.overlapDistance * (i - 1);
                if (sum >= 0.0f && areEnoughGirders(new ArrayList<>(copyOfGirders.values()))) {
                    girdersMap.put(new GirderValue(i, sum, max, isMain), newOption);
                }
            }
        }
        long startSortingTime = System.nanoTime();
        List<GirderValue> girderValues = selectionSort(new ArrayList<>(girdersMap.keySet()), this.accuracy);
        this.sortingTime = this.sortingTime + System.nanoTime() - startSortingTime;
        for (int i = 0; i < girderValues.size(); i++) {
            finalList.add(i, girdersMap.get(girderValues.get(i)));
        }
        this.get3BestTime = this.get3BestTime + System.nanoTime() - startTime;
        return finalList;
    }

    private static List<GirderValue> selectionSort(List<GirderValue> list, int numberOfBest) {
        int min,i,j;
        List<GirderValue> resList = new ArrayList<>();
        for (i = 0 ; i < min(numberOfBest, list.size()) ; i++)  {
            min=i;
            for (j = i+1 ; j < list.size() ; j++)
                if (list.get(j).compareTo(list.get(min)) < 0)
                    min=j;
            resList.add(list.get(min));
            Collections.swap(list, i, min);
        }

        return resList;
    }

    private boolean isUpperRowValid(List<Float> girdersKey, List<Integer> positions, int min, float distance) {
        int n = positions.size();
        //TODO have to take into account that theres additional 2 x 0,3 m near the walls
        //for max len = 6.6 m
        //TODO len more than 6.6 m
        //true
        if (min == 1) return true;
        if (n == 1 && min == 2) return true;
        int dif = min - n;
        List<Float> lensOfGirders = new ArrayList<>();
        for (Integer position : positions) lensOfGirders.add(girdersKey.get(position));
        return isGirderListValid(lensOfGirders, distance, dif, min);
    }

    private boolean isGirderListValid(List<Float> originList, float distance, int howMany, int min) {
        int n = 0;
        if (howMany <= 0) {
            for (Float number : originList) if (number > distance + this.upperExtraDistance) n++;
            return n >= 2;
        }

        for (Float number : originList) if (number > 2 * distance) n++;

        return n >= howMany;
    }

    private int girdersLeft(List<Integer> girders) {
        int sum = 0;
        for (Integer integer : girders)
            sum = sum + integer;
        return sum;
    }

    private int girdersLeftValue(HashMap<Float, Integer> girders) {
        int sum = 0;
        for (Float fl : girders.keySet()) {
            if (fl == 2.0f) sum = sum + girders.get(fl);
            if (fl == 2.45f) sum = sum + 2 * girders.get(fl);
            if (fl == 2.65f) sum = sum + 3 * girders.get(fl);
            if (fl == 3.0f) sum = sum + 4 * girders.get(fl);
            if (fl == 3.30f) sum = sum + 5 * girders.get(fl);
        }
        return sum;
    }

    private boolean areEnoughGirders(List<Integer> numberOfGirders) {
        long startTime = System.nanoTime();
        for(Integer number : numberOfGirders) if(number < 0) {
            this.areEnoughGirdersTime = this.areEnoughGirdersTime + System.nanoTime() - startTime;
            return false;
        }
        this.areEnoughGirdersTime = this.areEnoughGirdersTime + System.nanoTime() - startTime;
        return true;

    }

    private int calculateNumberOfRows(float roomsLen, float distance, boolean isMain) {
        if (isMain) roomsLen = roomsLen - 0.6f;
        return (int) Math.ceil(roomsLen / distance) + 1;
    }

    private float calculateDistance(float roomsLen, int numberOfGirders, boolean isMain) {
        float offset = isMain ? 0.6f : 0.0f;
        return (roomsLen - offset) / (numberOfGirders - 1);
    }

    private Set<List<Integer>> getCombinations(int n, int k) {
        if(this.combinations.get(k) != null) return this.combinations.get(k);
        long startTime = System.nanoTime();
        Set<List<Integer>> finalSet;
        String[] intsAsString = IntStream.range(0, n)
                .mapToObj(Integer::toString)
                .toArray(String[]::new);

        finalSet = getSetOfCombinations(getAllLists(intsAsString, k));
        this.combinations.put(k, finalSet);
        this.getCombinationsTime = this.getCombinationsTime + System.nanoTime() - startTime;
        return finalSet;
    }

    private Set<List<Integer>> getSetOfCombinations(String[] combinations) {
        Set<List<Integer>> combinationSet = new HashSet<>();
        for (String str : combinations) {
            List<Integer> positions = Arrays.stream(str.split(""))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .sorted()
                    .collect(Collectors.toList());

            combinationSet.add(positions);
        }
        return combinationSet;
    }

    private String[] getAllLists(String[] elements, int lengthOfList) {
        //initialize our returned list with the number of elements calculated above
        String[] allLists = new String[(int) Math.pow(elements.length, lengthOfList)];

        //lists of length 1 are just the original elements
        if (lengthOfList == 1) return elements;
        else {
            //the recursion--get all lists of length 3, length 2, all the way up to 1
            String[] allSublists = getAllLists(elements, lengthOfList - 1);

            //append the sublists to each element
            int arrayIndex = 0;

            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < allSublists.length; j++) {
                    //add the newly appended combination to the list
                    allLists[arrayIndex] = elements[i] + allSublists[j];
                    arrayIndex++;
                }
            }
            return allLists;
        }
    }

    private int girdersNeeded(List<Integer> list) {
        int sum = 0;
        for (Integer integer : list)
            if (integer < 0) sum = sum + Math.abs(integer);
        return sum;
    }

    private void buildResultString(Room room, int rows, float distance, List<Float> girders, boolean isMain){
        String name1, name2;
        if (isMain) {
            name1 = "dolnych";
            name2 = "dolnymi";
        } else {
            name1 = "górnych";
            name2 = "górnymi";
        }

        if (!isMain)
            resultString.append("\nPokoj: ").append(room.getLen()).append(" na ").append(room.getWid()).append("\n");

        resultString.append("Liczba rzędów ").append(name1).append(" dźwigarów: ").append(rows).append("\n");
        resultString.append("Dystans pomiedzy ").append(name2).append(" dźwigarami: ").append(distance).append("\n");
        resultString.append("Potrzebne dźwigary: ").append(girders).append("\n\n");

    }

    private void setFormworkedRoom(boolean isMain, int indexOfRoom, Room room, int rows,
                                   List<Float> girders, int distance ){
        FormworkedRoom formworkedRoom;

        if(isMain){
            formworkedRoom = finalFormwork.get(indexOfRoom);
            formworkedRoom.setLength((int) (room.getLen() * 100));
            formworkedRoom.setWidth((int) (room.getWid() * 100));
            formworkedRoom.setBottomRows(rows);
            formworkedRoom.setBottomGirders(girders);
            formworkedRoom.setBottomDistance(distance);
            finalFormwork.put(indexOfRoom, formworkedRoom);
        } else {
            formworkedRoom = new FormworkedRoom();
            formworkedRoom.setUpperRows(rows);
            formworkedRoom.setUpperGirders(girders);
            formworkedRoom.setUpperDistance(distance);
            finalFormwork.put(indexOfRoom, formworkedRoom);
        }
    }

    public void showTimeResults(){
        System.out.println("\nBuild time: " + this.buildTime / 1000000 + " ms");
        System.out.println("Get Best Options: " + this.get3BestTime / 1000000 + " ms");
        System.out.println("Are Enough Girders: " + this.areEnoughGirdersTime / 1000000 + " ms");
        System.out.println("Get Combinations: " + this.getCombinationsTime / 1000000 + " ms");
        System.out.println("Sorting: " + this.sortingTime / 1000000 + " ms");
        System.out.println("Full time: " + (this.buildTime + this.get3BestTime
                + this.areEnoughGirdersTime + this.getCombinationsTime) / 1000000 + " ms");
    }
}
