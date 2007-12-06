package javabot.admin;

import java.io.File;

import javabot.javadoc.StructureDoclet;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 * Created Jul 19, 2007
 *
 * @author <a href="mailto:javabot@cheeseronline.org">cheeser</a>
 */
public class JavadocPanel extends Panel {
    public JavadocPanel(String id) {
        super(id);
        add(new FileUploadForm("form"));
    }

    private class FileUploadForm extends Form {
        private String srcZipPath = "/tmp/src.zip";
        private String packages = "java javax org";

        public FileUploadForm(String name) {
            super(name);
            add(new TextField("file", new PropertyModel(this, "srcZipPath")));
            add(new TextField("packages", new PropertyModel(this, "packages")));
        }

        public String getSrcZipPath() {
            return srcZipPath;
        }

        public void setSrcZipPath(String filePath) {
            srcZipPath = filePath;
        }

        public String getPackages() {
            return packages;
        }

        public void setPackages(String pkgs) {
            packages = pkgs;
        }
        
        @Override
        protected void onSubmit() {
            if(srcZipPath != null) {
                try {
                    new StructureDoclet().parse(new File(srcZipPath), packages);
                } catch(Exception e) {
                    error(e.getMessage());
                }

                info("done");
            }
        }
    }


}