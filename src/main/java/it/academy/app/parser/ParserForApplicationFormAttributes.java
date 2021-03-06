package it.academy.app.parser;

import it.academy.app.models.ApplicationForm;
import org.apache.commons.lang3.text.WordUtils;


public class ParserForApplicationFormAttributes {
    public ApplicationForm parse(ApplicationForm applicationForm) {
        String formattedName = wordFirstLetterToUppercase(deleteExtraSpaces(applicationForm.getName()));
        applicationForm.setName(formattedName);

        String formattedSurname = wordFirstLetterToUppercase(deleteExtraSpaces(applicationForm.getSurname()));
        applicationForm.setSurname(formattedSurname);

        applicationForm.setEmail(applicationForm.getEmail().trim());

        applicationForm.setPhone(formatTelephoneNumber(applicationForm.getPhone()));

        String formattedEducation = deleteExtraSpaces(applicationForm.getEducation());
        applicationForm.setEducation(formattedEducation);

        applicationForm.setFreeTimeActivity(deleteExtraSpaces(applicationForm.getFreeTimeActivity()));
        applicationForm.setMotivation(deleteExtraSpaces(applicationForm.getMotivation()));
        applicationForm.setExperience(deleteExtraSpaces(applicationForm.getExperience()));
        applicationForm.setInfoAboutAcademy(deleteExtraSpaces(applicationForm.getInfoAboutAcademy()));
        return applicationForm;
    }

    private String deleteExtraSpaces(String attribute) {
        return attribute.trim().replaceAll(" +", " ");
    }

    private String wordFirstLetterToUppercase(String attribute) {
        attribute = WordUtils.capitalizeFully(attribute);

        if (attribute.indexOf('-') > -1) {
            for (int i = 1; i < attribute.length(); ++i) {
                if (attribute.charAt(i - 1) == '-') {
                    attribute = attribute.substring(0, i) + Character.toUpperCase(attribute.charAt(i)) + attribute.substring(i + 1);
                }
            }
        }
        return attribute;
    }

    private String formatTelephoneNumber(String attribute) {
        return attribute.substring(0, 5) + "-" + attribute.substring(5, 7) + "-" + attribute.substring(7);
    }
}
