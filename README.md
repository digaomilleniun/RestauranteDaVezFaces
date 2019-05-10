# RestauranteDaVezFaces
Teste DBServer

# Instalação

1 - Baixar e descompactar o servidor wildfly.

2 - Configurar o driver do Postgresql como módulo do wildfly
- Criar as pastas postgresql/main no caminho {SERVER}/modules/system/layers/base/org 
- Adicionar o jar do driver nesta pasta.
- Criar o arquivo com o nome module.xml com o conteúdo abaixo, na pasta acima

# Markdown
<?xml version="1.0" encoding="UTF-8"?>
<module name="org.postgresql" xmlns="urn:jboss:module:1.3">
  <resources>
    <resource-root path="postgresql.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
