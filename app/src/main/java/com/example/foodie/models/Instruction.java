package com.example.foodie.models;

import java.util.List;

public class Instruction {
    private int instructionId;
    private int step;
    private String instruction;
    private int recipeId;

    // retrieved db item constructor
    public Instruction(int instructionId, int step, String instruction, int recipeId){
        this.instructionId = instructionId;
        this.step = step;
        this.instruction = instruction;
        this.recipeId = recipeId;
    }

    // new item construction
    public Instruction(int step, String instruction, int recipeId){
        this(0, step, instruction, recipeId);
    }

    // getters
    public int getInstructionId(){return instructionId;}
    public int getStep(){return step;}
    public String getInstruction(){return instruction;}
    public int getRecipeId(){return recipeId;}

    // setters
    public void setInstructionId(int instructionId){this.instructionId = instructionId;}
    public void setStep(int step){this.step = step;}
    public void setInstruction(String instruction){this.instruction = instruction;}
    public void setRecipeId(int recipeId){this.recipeId = recipeId;}

    // display method
    public String displayInstructions(Recipe recipe){
        List<String> instructions = recipe.getInstructions();
        StringBuilder instructionsBuilder = new StringBuilder();
        int stepCount = 1;
        for(String item : instructions){
            instructionsBuilder.append("Step ")
                    .append(stepCount + " ")
                    .append(item);
            stepCount++;
        }
        return instructionsBuilder.toString();
    }

    // override
    @Override
    public String toString(){
        return "ID: " + instructionId +
                "\nStep: " + step +
                "\nInstruction: " + instruction +
                "\nRecipe: " + recipeId;
    }
}
