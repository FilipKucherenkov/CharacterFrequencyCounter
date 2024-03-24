package org.fnktech.model;

import java.util.ArrayList;
import java.util.List;

public class TxtFile {

    private List<String> content;

    public TxtFile(){
        content = new ArrayList<>();
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
