package ru.vyukov.bakapa.admin.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class SuperUIController {

    private static final String SUCCESS_MESSAGE_GLOBAL = "successMessageGLOBAL";
    private static final String DANGER_MESSAGE_GLOBAL = "dangerMessageGLOBAL";


    public void successMessage(Model model, String text) {
        Map<String, Object> map = model.asMap();
        add(text, map, SUCCESS_MESSAGE_GLOBAL);
    }

    public void successMessage(RedirectAttributes redirectAttributes, String text) {
        Map<String, Object> map = (Map<String, Object>) redirectAttributes.getFlashAttributes();
        add(text, map, SUCCESS_MESSAGE_GLOBAL);
    }


    private void add(String text, Map<String, Object> map, String param) {
        List<String> messagesGLOBAL = (List<String>) map.computeIfAbsent(param, (key) -> new ArrayList<String>());
        messagesGLOBAL.add(text);
    }


    public void dangerMessage(Model model, String text) {
        Map<String, Object> map = model.asMap();
        add(text, map, DANGER_MESSAGE_GLOBAL);
    }

    public void dangerMessage(RedirectAttributes redirectAttributes, String text) {
        Map<String, Object> map = (Map<String, Object>) redirectAttributes.getFlashAttributes();
        add(text, map, DANGER_MESSAGE_GLOBAL);
    }

    protected void dangerMessage(Model model, BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getGlobalErrors();
        allErrors.forEach(objectError -> {
            dangerMessage(model, objectError.getDefaultMessage());
        });

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            dangerMessage(model, message);
        });
    }
}
