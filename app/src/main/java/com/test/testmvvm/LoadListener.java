package com.test.testmvvm;

import java.util.List;

public interface LoadListener <Book>{
    void loadSuccess(List<Book> list);
    void loadFailure(String s);
}
