package tk.jasonho.shortcuts.bukkit.util.universal.craft;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CustomShapedRecipe {
    private final List<ItemStack> recipe;
    @Getter
    private final ItemStack result;
    private final int amount;

    public CustomShapedRecipe(ItemStack result, int amount, ItemStack... metas) {
        if (result == null || amount <= 0) {
            throw new IllegalArgumentException("Result can't be null or you must give the player at least one item!");
        }

        if (metas.length != 9) {
            throw new IllegalArgumentException("Illegal argument size for custom recipe!");
        }

        List<ItemStack> metaList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            metaList.add(i, metas[i] == null ? new ItemStack(Material.AIR) : metas[i]);
        }

        this.recipe = metaList;
        this.result = result;
        this.amount = amount;
    }

    public boolean resultMatches(ItemStack m) {
        boolean d = this.result.getItemMeta().getDisplayName() == m.getItemMeta().getDisplayName();
        boolean ma = this.result.getData().getItemType() == m.getData().getItemType();
        return ma && d;
    }

    public int getMatchAmount(CraftingInventory i) {
        int am = 0;
        if (this.recipeMatches(i)) {
            for (int i1 = 0; i1 < 9; i1++) {
                if (i.getMatrix()[i1].getAmount() > am) {
                    am = i.getMatrix()[i1].getAmount();
                }
                if (i.getMatrix()[i1].getAmount() < am) {
                    am -= i.getMatrix()[i1].getAmount();
                    return am;
                }
            }
        }
        return am;
    }

    public boolean recipeMatches(CraftingInventory i) {
        for (int i1 = 0; i1 < 9; i1++) {
            // Air/empty slots
            if ((this.recipe.get(i1).getType() == Material.AIR) && (i.getMatrix()[i1]).getType() == Material.AIR) {
                continue;
            }

            if (this.recipe.get(i1).getType() != i.getMatrix()[i1].getType()) {
                return false;
            }

            // Filled slots
            if (i.getMatrix()[i1].getItemMeta().getDisplayName().equalsIgnoreCase(this.recipe.get(i1).getItemMeta().getDisplayName())) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


}

