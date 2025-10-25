package mediaapp.com.just4you.Validators.validator;



import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mediaapp.com.just4you.Validators.annotations.DominioValido;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public  class ValidadorDominio implements ConstraintValidator<DominioValido, String> {

    private final Set<String> dominiosPermitidos = new HashSet<>();
    private final Set<String> protocolosPermitidos = new HashSet<>();

    @Override
    public void initialize(DominioValido constraintAnnotation) {

        this.dominiosPermitidos.clear();
        this.protocolosPermitidos.clear();


        this.dominiosPermitidos.addAll(Arrays.asList(constraintAnnotation.dominiosPermitidos()));

        for (String protocolo : constraintAnnotation.protocolosPermitidos()){
            this.protocolosPermitidos.add(protocolo.toLowerCase());
        }
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext contexto){
        if(url == null || url.isBlank()){
            return true;
        }

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            return false;
        }

        if(!this.protocolosPermitidos.isEmpty()){
            String protocolo = uri.getScheme();
            if(protocolo == null || !this.protocolosPermitidos.contains(protocolo.toLowerCase())){
                return false;
            }
        }

        String host = uri.getHost();
        if(host == null) return false;
        for (String dominio : this.dominiosPermitidos) {
            if(host.equals(dominio) || host.endsWith("." + dominio)) return true;
        }

        return false;
    }

}
