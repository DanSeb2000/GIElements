package me.danseb.elements;


import me.danseb.elements.combinations.ElementsCombinations;
import me.danseb.elements.combinations.types.*;
import org.bukkit.Color;

public enum Elements {
    ELECTRO(Color.FUCHSIA, new ElectroCombination()),
    GEO(Color.YELLOW, new GeoCombination()),
    ANEMO(Color.LIME, new AnemoCombination()),
    CRYO(Color.AQUA, new CryoCombination()),
    PYRO(Color.RED, new PyroCombination()),
    DENDRO(Color.GREEN, new DendroCombination()),
    HYDRO(Color.BLUE, new HydroCombination());

    private final Color color;
    private final ElementsCombinations combinations;

    Elements (Color color, ElementsCombinations combinations){
        this.color = color;
        this.combinations = combinations;
    }

    public Color getColor() {
        return color;
    }

    public ElementsCombinations getCombinations() {
        return combinations;
    }
}
