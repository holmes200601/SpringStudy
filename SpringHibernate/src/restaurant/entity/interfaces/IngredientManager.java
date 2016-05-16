package restaurant.entity.interfaces;

import java.math.BigDecimal;

import restaurant.masterdata.bean.entity.Ingredient;
import restaurant.uom.bean.entity.Uom;

public interface IngredientManager {
    public boolean changeIngredientAmount(Long ingredientId, BigDecimal deltaAmount, Long uomId);

    public boolean changeIngredientAmount(Ingredient ingredient, BigDecimal deltaAmount, Uom uom);
}
