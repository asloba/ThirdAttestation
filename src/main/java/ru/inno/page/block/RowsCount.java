package ru.inno.page.block;

public enum RowsCount {

    ROWS_5("5"),
    ROWS_10("10"),
    ROWS_20("20"),
    ROWS_25("25"),
    ROWS_50("50"),
    ROWS_100("100");

    private String name;

    RowsCount(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
