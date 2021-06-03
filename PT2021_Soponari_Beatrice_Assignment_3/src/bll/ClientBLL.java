package bll;

import model.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import validator.EmailValidator;
import validator.Validator;
public class ClientBLL{
    private List<Validator<Client>> validators;
    public ClientBLL(){
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
    }
}
