package cu.edu.cujae.pweb.config;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;


@RewriteConfiguration
public class UrlRewriteConfigurationProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()
                
        		.addRule(Join.path("/security-users").to("/pages/security/users/user-list.jsf"))
        		.addRule(Join.path("/").to("/pages/security/login.jsf"))
        		.addRule(Join.path("/welcome").to("/pages/welcome/welcome.jsf"))
        		.addRule(Join.path("/clients-list").to("/pages/security/clientes/lista_clientes.jsf"))
        		.addRule(Join.path("/almacen-list").to("/pages/almacen/almacenInfo.jsf"))
        		.addRule(Join.path("/carga-list").to("/pages/cargas/lista_cargas.jsf"))
        		.addRule(Join.path("/company-list").to("/pages/companys/company-list.jsf"))
        		.addRule(Join.path("/services-list").to("/pages/services/lista_servicios.jsf"))
        		.addRule(Join.path("/new-import").to("/pages/services/nueva_import.jsf"))
        		.addRule(Join.path("/new-export").to("/pages/services/nueva_export.jsf"))
        		.addRule(Join.path("/general-report").to("/pages/reportes/reporteservicios.jsf"))
        		.addRule(Join.path("/servicios-report").to("/pages/reportes/reportgeneral.jsf"))
        		.addRule(Join.path("/almacen-report").to("pages/reportes/almacenReport.jsf"));
        //.addRule(Join.path("/shop/{category}").to("/faces/viewCategory.jsf"));
    }

    @Override
    public int priority() {
        return 0;
    }

}

