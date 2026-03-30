# REFLEXION

**1. ¿Qué es la serialización y cuándo es útil en comparación con guardar
   texto plano?**

Serializar es cuando un objeto se convierte en una secuencia de bytes. Además, permite almacenar dicho objeto en un archivo para ser enviador por la red, o simplemente para usarlo más tarde. Es especialmente útil cuando se quiere guardar un objeto y sus estados, permitiendo así recuperarlo en caso de fallos, errores o pérdidas.

**2. ¿Por qué usamos =BufferedReader= en lugar de leer byte a byte? ¿Qué
   mejora en rendimiento ofrece?**

Porque `BufferedReader`lee los textos almacenando los datos en bloques de memoria, en lugar de caracter a caracter o byte a byte; esto ayuda a la eficiencia del manejo de datos.

**3. ¿Qué riesgos tiene no cerrar un archivo después de usarlo? ¿Cómo los
   mitigaste?**

De manera similar a los punteros en C o C++, si no se cierra un archivo después de ser usado, puede causar fugas de memoria; así mismo, otras complicaciones que puede presentar son la pérdida de datos o el bloqueo del archivo mismo, pues el sistema 'detectaría' que el archivo en cuestión 'sigue en uso'. Para contrarrestar eso, se hizo uso de las herramientas como `BufferedReader`, `BufferedWriter`, y `FileOutputStream`.