package FileManger;
import java.awt.Image; 
import java.awt.Toolkit; 
import java.io.BufferedInputStream; 
import java.io.ByteArrayOutputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.util.HashMap; 
import java.util.Iterator; 
import javax.swing.ImageIcon; 

public class SwingResourceManager { 

private static HashMap <String, Image> m_ClassImageMap = new HashMap <String, Image>(); 

private static Image getImage(InputStream is) { 
try { 
ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
byte buf[] = new byte[1024 * 4]; 
while (true) { 
int n = is.read(buf); 
if (n == -1) 
break; 
baos.write(buf, 0, n); 
} 
baos.close(); 
return Toolkit.getDefaultToolkit().createImage(baos.toByteArray()); 
} catch (Throwable e) { 
return null; 
} 
} 

public static Image getImage(Class clazz, String path) { 
String key = clazz.getName() + "|" + path; 
Image image = m_ClassImageMap.get(key); 
if (image == null) { 
if ((path.length() > 0) && (path.charAt(0) == '/')) { 
String newPath = path.substring(1, path.length()); 
image = getImage(new BufferedInputStream(clazz.getClassLoader().getResourceAsStream(newPath))); 
} else { 
image = getImage(clazz.getResourceAsStream(path)); 
} 
m_ClassImageMap.put(key, image); 
} 
return image; 
} 


public static Image getImage(String path) { 
return getImage("default", path); //$NON-NLS-1$ 
} 


public static Image getImage(String section, String path) { 
String key = section + "|" + SwingResourceManager.class.getName() +"|" + path; 
Image image = m_ClassImageMap.get(key); 
if (image == null) { 
try { 
FileInputStream fis = new FileInputStream(path); 
image = getImage(fis); 
m_ClassImageMap.put(key, image); 
fis.close(); 
} catch (IOException e) { 
return null; 
} 
} 
return image; 
} 

public static void clearImages(String section) { 
for (Iterator I = m_ClassImageMap.keySet().iterator(); I.hasNext();) { 
String key = (String) I.next(); 
if (!key.startsWith(section + "|")) 
continue; 
Image image = m_ClassImageMap.get(key); 
image.flush(); 
I.remove(); 
} 
} 


public static ImageIcon getIcon(Class clazz, String path) { 
return getIcon(getImage(clazz, path)); 
} 


public static ImageIcon getIcon(String path) { 
return getIcon("default", path); //$NON-NLS-1$ 
} 

public static ImageIcon getIcon(String section, String path) { 
return getIcon(getImage(section, path)); 
} 
public static ImageIcon getIcon(Image image) { 
if (image == null) 
return null; 
return new ImageIcon(image); 
} 
} 
