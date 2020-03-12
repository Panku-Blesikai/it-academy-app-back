package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DemoControllerTests {

    public static final String MESSAGE = "DB works";
    @Mock
    private DemoRepository repository;

    @InjectMocks
    private DemoController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldBEandDBWork() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Message(MESSAGE)));
        assertEquals(DemoController.BE_SUCCESS_MESSAGE + MESSAGE, controller.helloWorld());
    }
    @Test
    public void shouldWorkOnlyBE() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(DemoController.BE_SUCCESS_MESSAGE, controller.helloWorld());
    }
}
