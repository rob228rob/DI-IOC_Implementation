package org.example.SupportPackage;

public class SupportManager {

    private final SupportService supportService;

    public SupportManager(SupportService supportService) {
        this.supportService = supportService;
    }

    public String getSupportPhrase()
    {
        return SupportService.getRandomPhrase();
    }

    public void addSupportPhrase(String phrase)
    {
        SupportService.addNewPhrases(phrase);
    }

    public void providesupport()
    {
        System.out.println("providesupport");
        //supportServlet.doGet(SupportService.getRandomPhrase());
        System.out.println("end support");
    }
}
