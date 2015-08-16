echo off

cd %SystemDrive%\

if not exist "revenda" mkdir "revenda"
if not exist "revenda/generated" mkdir "revenda/generated"

"%JAVA_HOME%/bin/wsimport.exe" -clientjar "revenda/revenda_CLIENT_ARTIFACTS.jar" -verbose -s "revenda/generated" -keep "http://localhost:8080/revenda/?wsdl"

echo revenda_CLIENT_ARTIFACTS.jar gerado com sucesso.
echo Artefatos geradas em %SystemDrive%/revenda/generated

explorer %SystemDrive%\revenda

pause