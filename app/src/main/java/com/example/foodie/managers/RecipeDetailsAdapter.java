//package com.example.foodie.managers;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.foodie.R;
//import com.example.foodie.models.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//// RecipeDetailAdapter.java
//public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Recipe recipe;
//    private List<RecipeItem> items = new ArrayList<>();
//
//    private OnAddIngredientListener onAddIngredient;
//    private OnAddInstructionListener onAddInstruction;
//    private OnDeleteIngredientListener onDeleteIngredient;
//    private OnDeleteInstructionListener onDeleteInstruction;
//
//    private static final int VIEW_TYPE_HEADER = 0;
//    private static final int VIEW_TYPE_INGREDIENT = 1;
//    private static final int VIEW_TYPE_INSTRUCTION = 2;
//    private static final int VIEW_TYPE_ADD_INGREDIENT = 3;
//    private static final int VIEW_TYPE_ADD_INSTRUCTION = 4;
//
//    public RecipeDetailsAdapter(
//            Recipe recipe,
//            OnAddIngredientListener onAddIngredient,
//            OnAddInstructionListener onAddInstruction,
//            OnDeleteIngredientListener onDeleteIngredient,
//            OnDeleteInstructionListener onDeleteInstruction) {
//
//        this.recipe = recipe;
//        this.onAddIngredient = onAddIngredient;
//        this.onAddInstruction = onAddInstruction;
//        this.onDeleteIngredient = onDeleteIngredient;
//        this.onDeleteInstruction = onDeleteInstruction;
//        buildItemList();
//    }
//
//    private void buildItemList() {
//        items.clear();
//
//        // Add Ingredients section
//        items.add(new RecipeItem(RecipeItem.TYPE_HEADER, "Ingredients", null, null));
//
//        for (String ingredient : recipe.getIngredients()) { // iterating through ingredients in a recipe
//            items.add(new RecipeItem(RecipeItem.TYPE_INGREDIENT, null, ingredient, null));
//        }
//        items.add(new RecipeItem(RecipeItem.TYPE_ADD_INGREDIENT, null, null, null));
//
//        // Add Instructions section
//        items.add(new RecipeItem(RecipeItem.TYPE_HEADER, "Instructions", null, null));
//        for (String instruction : recipe.getInstructions()) {// iterating through instructions in a recipe
//            items.add(new RecipeItem(RecipeItem.TYPE_INSTRUCTION, null, null, instruction));
//        }
//        items.add(new RecipeItem(RecipeItem.TYPE_ADD_INSTRUCTION, null, null, null));
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return items.get(position).getType();
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//        switch (viewType) {
//            case VIEW_TYPE_HEADER:
//                return new HeaderViewHolder(inflater.inflate(R.layout.item_section_header, parent, false));
//            case VIEW_TYPE_INGREDIENT:
//                return new IngredientCardViewHolder(inflater.inflate(R.layout.item_ingredient_card, parent, false));
//            case VIEW_TYPE_INSTRUCTION:
//                return new InstructionCardViewHolder(inflater.inflate(R.layout.item_instruction_card, parent, false));
//            case VIEW_TYPE_ADD_INGREDIENT:
//                return new AddIngredientViewHolder(inflater.inflate(R.layout.item_add_input, parent, false));
//            case VIEW_TYPE_ADD_INSTRUCTION:
//                return new AddInstructionViewHolder(inflater.inflate(R.layout.item_add_input, parent, false));
//            default:
//                throw new IllegalArgumentException("Unknown view type: " + viewType);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        RecipeItem item = items.get(position);
//
//        if (holder instanceof HeaderViewHolder) {
//            ((HeaderViewHolder) holder).bind(item.getTitle());
//        } else if (holder instanceof IngredientCardViewHolder) {
//            ((IngredientCardViewHolder) holder).bind(item.getIngredient());
//        } else if (holder instanceof InstructionCardViewHolder) {
//            ((InstructionCardViewHolder) holder).bind(item.getInstruction());
//        } else if (holder instanceof AddIngredientViewHolder) {
//            ((AddIngredientViewHolder) holder).bind();
//        } else if (holder instanceof AddInstructionViewHolder) {
//            ((AddInstructionViewHolder) holder).bind();
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    // ========== ViewHolders ==========
//
//    private class HeaderViewHolder extends RecyclerView.ViewHolder {
//        private TextView title;
//
//        HeaderViewHolder(View itemView) {
//            super(itemView);
//            title = itemView.findViewById(R.id.section_title);
//        }
//
//        void bind(String sectionTitle) {
//            title.setText(sectionTitle);
//        }
//    }
//
//    private class IngredientCardViewHolder extends RecyclerView.ViewHolder {
//        private EditText ingredientText;
//        private Button deleteBtn;
//
//        IngredientCardViewHolder(View itemView) {
//            super(itemView);
//            ingredientText = itemView.findViewById(R.id.item);
//            deleteBtn = itemView.findViewById(R.id.delete_btn);
//        }
//
//        void bind(Ingredient ingredient) {
//            ingredientText.setText(ingredient.getName());
//            ingredientText.setEnabled(false);
//            deleteBtn.setOnClickListener(v -> {
//                onDeleteIngredient.onDelete(ingredient.getId());
//                recipe.getIngredients().remove(ingredient);
//                buildItemList();
//                notifyDataSetChanged();
//            });
//        }
//    }
//
//    private class InstructionCardViewHolder extends RecyclerView.ViewHolder {
//        private EditText instructionText;
//        private Button deleteBtn;
//
//        InstructionCardViewHolder(View itemView) {
//            super(itemView);
//            instructionText = itemView.findViewById(R.id.item);
//            deleteBtn = itemView.findViewById(R.id.delete_btn);
//        }
//
//        void bind(Instruction instruction) {
//            instructionText.setText(instruction.getStep());
//            instructionText.setEnabled(false);
//            deleteBtn.setOnClickListener(v -> {
//                onDeleteInstruction.onDelete(instruction.getId());
//                recipe.getInstructions().remove(instruction);
//                buildItemList();
//                notifyDataSetChanged();
//            });
//        }
//    }
//
//    private class AddIngredientViewHolder extends RecyclerView.ViewHolder {
//        private EditText inputField;
//        private Button addBtn;
//
//        AddIngredientViewHolder(View itemView) {
//            super(itemView);
//            inputField = itemView.findViewById(R.id.item);
//            addBtn = itemView.findViewById(R.id.add_btn);
//        }
//
//        void bind() {
//            inputField.setHint("Enter ingredient...");
//            addBtn.setText("Add");
//            addBtn.setOnClickListener(v -> {
//                String text = inputField.getText().toString().trim();
//                if (!text.isEmpty()) {
//                    onAddIngredient.onAdd(text);
//                    recipe.getIngredients().add(text);
//                    inputField.setText("");
//                    buildItemList();
//                    notifyDataSetChanged();
//                }
//            });
//        }
//    }
//
//    private class AddInstructionViewHolder extends RecyclerView.ViewHolder {
//        private EditText inputField;
//        private Button addBtn;
//
//        AddInstructionViewHolder(View itemView) {
//            super(itemView);
//            inputField = itemView.findViewById(R.id.item);
//            addBtn = itemView.findViewById(R.id.add_btn);
//        }
//
//        void bind() {
//            inputField.setHint("Enter instruction...");
//            addBtn.setText("Add");
//            addBtn.setOnClickListener(v -> {
//                String text = inputField.getText().toString().trim();
//                if (!text.isEmpty()) {
//                    onAddInstruction.onAdd(text);
//                    recipe.getInstructions().add(text);
//                    inputField.setText("");
//                    buildItemList();
//                    notifyDataSetChanged();
//                }
//            });
//        }
//    }
//
//    // ========== RecipeItem Inner Class ==========
//
//    private static class RecipeItem {
//        static final int TYPE_HEADER = 0;
//        static final int TYPE_INGREDIENT = 1;
//        static final int TYPE_INSTRUCTION = 2;
//        static final int TYPE_ADD_INGREDIENT = 3;
//        static final int TYPE_ADD_INSTRUCTION = 4;
//
//        private int type;
//        private String title;
//        private String ingredient;
//        private String instruction;
//
//        RecipeItem(int type, String title, String ingredient, String instruction) {
//            this.type = type;
//            this.title = title;
//            this.ingredient = ingredient;
//            this.instruction = instruction;
//        }
//
//        int getType() { return type; }
//        String getTitle() { return title; }
//        String getIngredient() { return ingredient; }
//        String getInstruction() { return instruction; }
//    }
//
//    // ========== Callback Interfaces ==========
//
//    public interface OnAddIngredientListener {
//        void onAdd(String ingredient);
//    }
//
//    public interface OnAddInstructionListener {
//        void onAdd(String instruction);
//    }
//
//    public interface OnDeleteIngredientListener {
//        void onDelete(int ingredientId);
//    }
//
//    public interface OnDeleteInstructionListener {
//        void onDelete(int instructionId);
//    }
//}