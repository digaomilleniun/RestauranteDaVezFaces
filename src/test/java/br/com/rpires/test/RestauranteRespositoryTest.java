/**
 * 
 */
package br.com.rpires.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.rpires.restaurante.entity.Restaurante;
import br.com.rpires.restaurante.repository.IRestauranteRespository;

/**
 * @author digao
 *
 */
@Ignore
@RunWith(Arquillian.class)
public class RestauranteRespositoryTest {
	
	@Inject
	private IRestauranteRespository respository;

	@Deployment(name="depl1")
	@TargetsContainer("weld")
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(Restaurante.class)
            .addAsManifestResource("META-INF/persistence.xml", "/META-INF/persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @OperateOnDeployment("depl1")
    public void save() {
        Restaurante rest = new Restaurante();
        rest.setNome("Teste");
        respository.salvar(rest);
    }
}
