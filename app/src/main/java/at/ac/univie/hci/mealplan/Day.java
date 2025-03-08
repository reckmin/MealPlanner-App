package at.ac.univie.hci.mealplan;

public class Day {
    private String name;
    private boolean expanded;

    public Day(String name) {
        this.name = name;
        this.expanded = false;
    }

    public String getName() {
        return name;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
