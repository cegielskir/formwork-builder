package com.cegielskir.formwork.builder.computing;

import com.cegielskir.formwork.builder.entity.GirderSet;
import com.cegielskir.formwork.builder.entity.Room;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class FormworkBuilder implements Runnable{


    private Formwork formwork;
    private boolean isBetterOptionAvailable = true;
    private boolean isBetterSolutionCalculated = false;
    private String resultJSON = null;

    public FormworkBuilder(List<Room> rooms, List<GirderSet> girderSets) {
        this.formwork = new Formwork();
        this.formwork.addGirderSets(girderSets);
        this.formwork.addRooms(rooms);
    }

    @Override
    public void run() {
        try {

            HashMap<Float, Integer> res = null;

            if (isBetterOptionAvailable) {
                //TODO simple first execution of method 'build' without so many args
                res= formwork.build(formwork.getGirders(), true, 0,
                        0, 0, false, true);
                if (res != null) {
                    setBetterSolutionCalculated(true);
                } else {
                    setBetterOptionAvailable(false);
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            this.resultJSON = mapper.writeValueAsString(formwork.getFinalFormwork().values());


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public synchronized boolean isBetterOptionAvailable() {
        return isBetterOptionAvailable;
    }

    public synchronized boolean isBetterSolutionCalculated() {
        return isBetterSolutionCalculated;
    }

    public synchronized String getResultJSON() {
        return resultJSON;
    }

    public synchronized void setBetterOptionAvailable(boolean betterOptionAvailable) {
        isBetterOptionAvailable = betterOptionAvailable;
    }

    public synchronized void setBetterSolutionCalculated(boolean betterSolutionCalculated) {
        isBetterSolutionCalculated = betterSolutionCalculated;
    }

    public synchronized void setResultJSON(String resultJSON) {
        this.resultJSON = resultJSON;
    }

    public void calculateSolution() {
        Thread t = new Thread(this);
        t.start();
    }

        //IMPORTANT
        //len have to be larger than wid
//        formwork.addRoom(7.63f, 5.95f);
//        formwork.addRoom(7.45f, 5.95f);
//        formwork.addRoom(5.71f, 4.21f);
//        formwork.addRoom(4.88f, 4.55f);
//        formwork.addRoom(3.36f, 2.56f);
//        formwork.addRoom(2.56f, 2.35f);
//        formwork.addRoom(9.7f, 2.9f);


//        formwork.addRoom(3.36f, 2.56f);
//        formwork.addGirders(2.0f, 6);
//        formwork.addGirders(3.3f, 6);
//
//        formwork.addRoom(2.56f, 2.35f);
//        formwork.addGirders(2.0f, 4);
//        formwork.addGirders(2.45f, 6);
//
//
//        formwork.addRoom(4.88f, 4.55f);
//        formwork.addGirders(2.65f, 20);
//        formwork.addGirders(2.45f, 6);
//
//
//        formwork.addRoom(5.71f, 4.21f);
//        formwork.addGirders(3.0f, 6);
//        formwork.addGirders(2.0f, 13);
//        formwork.addGirders(2.45f, 13);
//
//
//        formwork.addRoom(9.7f, 2.9f);
//        formwork.addGirders(2.45f, 28);
//        formwork.addGirders(2.0f, 19);
//
//
//        formwork.addRoom(7.45f, 5.95f);
//        formwork.addGirders(2.0f, 4);
//        formwork.addGirders(2.45f, 32);
//        formwork.addGirders(3.0f, 24);
//
//
//        formwork.addRoom(7.63f, 5.95f);
//        formwork.addGirders(2.0f, 4);
//        formwork.addGirders(2.45f, 32);
//        formwork.addGirders(3.3f, 24);










        //  Buuilding
//        formwork.addRoom(7f, 5.62f); //living room
//        formwork.addRoom(5.15f, 3.45f); //room
//        formwork.addRoom(6.2f, 3.82f);
//        formwork.addRoom(6.07f, 6f);  //garage



            // left 52
//        formwork.addGirders(2.0f, 6);
//        formwork.addGirders(2.65f, 15);
//        formwork.addGirders(3.0f, 21);
//        formwork.addGirders(3.3f, 10);



 //undo commenting
//        formwork.addGirders(2.0f,30);
//        formwork.addGirders(2.45f, 110);
//        formwork.addGirders(2.65f,20);
//        formwork.addGirders(3f, 30);
//        formwork.addGirders(3.30f, 30);
//////
//////        //to buy
//        formwork.addGirders(2f, 20);
//        formwork.addGirders(2.45f, 20);
////
//        //        formwork.addRoom(2.35f, 2.56f);
//        formwork.removeGirders(2.45f, 6);
//        formwork.removeGirders(2f, 4);
//
////        formwork.addRoom(2.56f, 3.36f);
//        formwork.removeGirders(2f, 6);
//        formwork.removeGirders(3.3f, 6);


// Mokrzycka
//        //formwork.addRoom(2.4f, 2f);
//        formwork.removeGirders(3f, 2);
//        formwork.removeGirders(2f, 7);
//
//
////        formwork.addRoom(5.4f, 2.1f);
//        formwork.removeGirders(2f, 12);
//        formwork.removeGirders(3f, 4);
//
//
//        //formwork.addRoom(4.4f, 2.4f);
//        formwork.removeGirders(3f, 3);
//        formwork.removeGirders(2.45f, 12);
//
//
//        //formwork.addRoom(7.65f, 2.2f);
//        formwork.removeGirders(2.45f, 6);
//        formwork.removeGirders(3.30f, 10);
//        formwork.removeGirders(2f, 6);




        //currently used
        //formwork.removeGirders();


        // additional
//        formwork.addGirders(2.0f, 30);
//        formwork.addGirders(2.45f, 21);
//        //formwork.addGirders(2.0f, 30);
//        //formwork.addGirders(2.45f, 100);
//
//        //currently used
//        formwork.removeGirders(3.3f, 6);
//        formwork.removeGirders(2.0f, 10);
//        formwork.removeGirders(2.45f, 6);

        // exxtra




//        System.out.println(formwork.calculateNumberOfRows(4.0f, 0.5f));
//        System.out.println(formwork.calculateNumberOfRows(7.0f, 2f));
//        System.out.println(formwork.calculateNumberOfRows(5.6f, 0.5f));
//        System.out.println(formwork.calculateDistance(5.6f, 13, 0.0f));
//        System.out.println(formwork.calculateDistance(6.0f, 5, 0.0f));

//        try {
//
//            HashMap<Float, Integer> res = null;
//            HashMap<Float, Integer> newRes = null;
//
//
//            String command = "xd";
//            boolean stop = false;
//            Scanner odczyt = new Scanner(System.in);
//            while (!stop) {
//                switch (command) {
//                    case "j":
//                        System.out.println(" rooms : " + formwork.getFinalFormwork().values());
//                        odczyt.nextLine();
//                        break;
//                    case "b":
//                        String jsonString = " rooms : " + formwork.getFinalFormwork().values();
//                        ObjectMapper mapper = new ObjectMapper();
//                        mapper.writeValue(
//                                new File("result.json"),
//                                formwork.getFinalFormwork().values());
//                        break;
//                    case "c":
//                        if (isBetterOptionAvailable) {
//                            newRes = formwork.build(formwork.getGirders(), true, 0,
//                                    0, 0, false, true);
//                            if (newRes != null) {
//                                res = newRes;
//                                System.out.println("Obliczone");
//                            } else {
//                                isBetterOptionAvailable = false;
//                                System.out.println("Nie istnieje lepsze rozwiazanie dla zadanej precyzji");
//                            }
//                        } else {
//                            System.out.println("Nie istnieje lepsze rozwiązanie");
//                        }
//
//                        odczyt.nextLine();
//                        break;
//                    case "g":
//                        if (formwork.getGirders().keySet().size() == 0) {
//                            System.out.println("Nie ma obecnie żadnych dźwigarów");
//                        } else {
//                            for (Float fl : formwork.getGirders().keySet()) {
//                                System.out.println("Długość: " + fl + "  Liczba: " + formwork.getGirders().get(fl));
//                            }
//                        }
//                        odczyt.nextLine();
//                        break;
//                    case "r":
//                        if (formwork.getRooms().size() == 0) {
//                            System.out.println("Nie ma obecnie żadnych pokoi");
//                        } else {
//                            for (Room room : formwork.getRooms()) {
//                                System.out.println("Pokój: " + room.getLen() + "m  na  " + room.getWid() + "m");
//                            }
//                        }
//                        odczyt.nextLine();
//                        break;
//                    case "d":
//                        Scanner tmp = new Scanner(System.in);
//                        String str;
//                        float len;
//                        int numb;
//                        System.out.println("Podaj długość dźwigarów, które chcesz dodać (w metrach)");
//                        str = tmp.nextLine();
//                        len = Float.valueOf(str);
//                        System.out.println("Podaj ile dźwigarów chcesz dodać");
//                        str = tmp.nextLine();
//                        numb = Integer.valueOf(str);
//                        System.out.println("Dodaję " + numb + " dźwigarów o długości " + len + "m");
//                        isBetterOptionAvailable = true;
//                        formwork.addGirders(len, numb);
//                        odczyt.nextLine();
//
//                        break;
//                    case "s":
//                        Scanner tmp1 = new Scanner(System.in);
//                        String str1;
//                        float length;
//                        float width;
//                        System.out.println("Podaj długość pokoju");
//                        str1 = tmp1.nextLine();
//                        length = Float.valueOf(str1);
//                        System.out.println("Podaj szerokść pokoju");
//                        str1 = tmp1.nextLine();
//                        width = Float.valueOf(str1);
//                        System.out.println("Dodaję pokój " + length + "m  na   " + width + "m");
//                        formwork.addRoom(length, width);
//                        odczyt.nextLine();
//
//                        break;
//                    case "a":
//                        if (res == null) System.out.println("Nie ma rozwiązania");
//                        else {
//                            System.out.println(formwork.getResultString());
//                            int sum = 0;
//                            for (Integer integer : res.values()) sum = sum + integer;
//                            System.out.println("\nPozostało: " + res + "     Suma: " + sum + "\n\n\n\n");
//                            odczyt.nextLine();
//                        }
//                        break;
//                    case "m":
//                        formwork.showTimeResults();
//                        odczyt.nextLine();
//                        break;
//                    case "x":
//                        formwork.addGirders(2.0f, 100);
//                        formwork.addGirders(2.45f, 100);
//                        formwork.addGirders(2.65f, 100);
//                        formwork.addGirders(3.0f, 100);
//                        formwork.addGirders(3.3f, 100);
//                        System.out.println("Dźwigary dodane");
//                        odczyt.nextLine();
//                        break;
//                    case "q":
//                        stop = true;
//                        break;
//                }
//                if (!stop) {
//                    System.out.println("\nCo chcesz zrobić?");
//                    System.out.println("a - pokaż rozwiązanie");
//                    System.out.println("b - utwórz JSONa");
//                    System.out.println("c - oblicz lepsze rozwiazanie");
//                    System.out.println("j - pokaz rozwiazanie generowane do jsona");
//                    System.out.println("g - pokaz liste dźwigarów");
//                    System.out.println("d - dodaj dźwigar");
//                    System.out.println("r - pokaż liste pokoi");
//                    System.out.println("s - dodaj pokoj");
//                    System.out.println("x - dodaj po 100 wszystkich dzwigarow");
//                    System.out.println("m - pokaz czasy");
//                    System.out.println("q - zakoncz program\n");
//                    command = odczyt.nextLine();
//                }
//            }
//
//
//        } catch (IOException ex) {
//            System.out.println("IO exception");
//        }





}
