package med.voll.api.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object sourse) {
        // A classe BeanWrapperImpl é usada para acessar e manipular as propriedades de um objeto Java
        final BeanWrapper src = new BeanWrapperImpl(sourse);

        //O método getPropertyDescriptors é usado para obter um array de objetos PropertyDescriptor. Os objetos PropertyDescriptor descrevem as propriedades acessíveis de um objeto Java e fornecem informações sobre essas propriedades, como seus nomes e métodos de leitura/escrita associados.
        PropertyDescriptor[] properties = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        // Estou varrendo propertie, observando quais propriedades do objeto possuem valores nulos e inserindo elas no Set
        for (PropertyDescriptor pd : properties) {
            Object property = src.getPropertyValue(pd.getName());
            if (property == null) {
                emptyNames.add(pd.getName());
            }
        }
        // Crio um array do tamanho do Set, e depois 
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
