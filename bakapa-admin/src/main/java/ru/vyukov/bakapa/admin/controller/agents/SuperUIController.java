package ru.vyukov.bakapa.admin.controller.agents;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class SuperUIController {

    private static final String SUCCESS_MESSAGE_GLOBAL = "successMessageGLOBAL";
    private static final String DANGER_MESSAGE_GLOBAL = "dangerMessageGLOBAL";


    public void successMessage(String text, Model model) {
        Map<String, Object> map = model.asMap();
        add(text, map, SUCCESS_MESSAGE_GLOBAL);
    }

    public void successMessage(String text, RedirectAttributes redirectAttributes) {
        Map<String, Object> map = redirectAttributes.asMap();
        add(text, map, SUCCESS_MESSAGE_GLOBAL);
    }


    private void add(String text, Map<String, Object> map, String param) {
        List<String> messagesGLOBAL = (List<String>) map.computeIfAbsent(param, (key) -> new ArrayList<String>());
        messagesGLOBAL.add(text);
    }



    public void dangerMessage(String text, Model model) {
        Map<String, Object> map = model.asMap();
        add(text, map, DANGER_MESSAGE_GLOBAL);
    }

    public void dangerMessage(String text, RedirectAttributes redirectAttributes) {
        Map<String, Object> map = redirectAttributes.asMap();
        add(text, map, DANGER_MESSAGE_GLOBAL);
    }
}
