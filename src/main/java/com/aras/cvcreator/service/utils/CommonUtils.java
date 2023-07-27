package com.aras.cvcreator.service.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonUtils {

    /**
     * Add a string field to another with a comma separator. For example: "1, 2, 3"
     * @param string Existence string
     * @param newItem New item string
     * @return A string field that is concatenated with new item
     */
    public String addItemToStringByCommaSeparator(String string, String newItem) {
        return string == null || string.isEmpty() ? newItem : string.concat(", ").concat(newItem);
    }

    /**
     * Remove one item of a string that separated with comma
     * @param mainString Existence string
     * @param removedItem Item that should be removed
     * @return Main string without removed item
     */
    public String removeItemFromStringSeparatedByComma(String mainString, String removedItem) {
        if (mainString != null && !mainString.isEmpty() && mainString.contains(removedItem)) {
            if (mainString.endsWith(removedItem)) {
                mainString = mainString.replace(", ".concat(removedItem), "");
            }
            else {
                mainString = mainString.replace(removedItem.concat(", "), "");
            }
        }

        return mainString;
    }

    /**
     * Split a string of integers with a separator. If string is empty then return a new list.
     * @param string Existence string
     * @param separator Separator
     * @return A List of integers
     */
    public List splitStringOfIntegers(String string, String separator) {
        return string == null || string.isEmpty() ?
                new ArrayList() :
                Arrays.stream(string.split(separator)).map(item -> Integer.parseInt(item)).collect(Collectors.toList());
    }

    /**
     * Delete all files in directory except exceptions
     * @param dir Directory path
     * @param exceptions A list of the exception files name that we won't delete them. Name format example: image.jpg
     */
    public void emptyFolder(String dir, ArrayList<String> exceptions) {
        File file = new File(dir);
        File[] listFiles = file.listFiles();
        Arrays.stream(listFiles).filter(item -> !exceptions.contains(item.getName())).forEach(File::delete);
    }
}
