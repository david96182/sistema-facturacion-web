package cu.edu.cujae.pweb.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // todas las solicitudes deben estar autenticadas excepto las que se definan en este code
        http.authorizeRequests().antMatchers("/javax.faces.resource/**", "/resources/**", "/pages/security/login.jsf", 
        		"/pages/errors/**", "/pages/cargas/lista_cargas.jsf",
        		"/pages/services/lista_servicios.jsf", "/pages/security/clientes/lista_clientes.jsf",
        		"/pages/companys/company-list.jsf", "/pages/almacen/almacenInfo.jsf")
        .permitAll()
        .antMatchers("/pages/security/users/**").hasAnyAuthority("admin")
        .antMatchers("/pages/security/clientes/**").hasAnyAuthority("manager","comercial")
        .antMatchers("/pages/services/**").hasAnyAuthority("manager","comercial")
        .antMatchers("/pages/cargas/**").hasAnyAuthority("manager","comercial")
        .antMatchers("/pages/almacen/**").hasAnyAuthority("manager","comercial")
        .antMatchers("/pages/companys/**").hasAnyAuthority("manager","comercial")
        .antMatchers("/pages/reportes/**").hasAnyAuthority("manager","comercial")
        .anyRequest().authenticated();
        
 
        /*.antMatchers("/pages/security/clientes/**", "/pages/services/**", "/pages/almacen/**", "/pages/companys/**").hasAnyAuthority("comercial")
        .antMatchers("/pages/services/**", "/pages/almacen/**", "/pages/companys/**", "/pages/security/clientes/**").hasAnyAuthority("manager")*/
        
        // configurando el login
        http
        .exceptionHandling().accessDeniedPage("/pages/errors/access-denied.jsf");
        
        // logout, cuando se ejecute el logout va para el login
        http.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/pages/security/login.jsf");
        
        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();
    }
    
}