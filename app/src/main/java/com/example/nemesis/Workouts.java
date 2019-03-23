package com.example.nemesis;

public class Workouts {
    int id;
    int sets;
    int reps;
    String muscleGroup;
    String name;

    public Workouts(){

    }

    public Workouts(String name, String muscleGroup, int id, int sets, int reps) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.id = id;
        this.sets = sets;
        this.reps = reps;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public int getId() {
        return id;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }





}
