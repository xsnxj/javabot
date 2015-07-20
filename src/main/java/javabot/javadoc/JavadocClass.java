package javabot.javadoc;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.PrePersist;

import java.util.ArrayList;
import java.util.List;

@Entity(value = "classes", noClassnameStored = true)
@Indexes({
    @Index(fields = @Field("apiId")),
    @Index(fields = @Field("upperName")),
    @Index(fields = {@Field("upperPackageName"), @Field("upperName")}),
    @Index(fields = {@Field("apiId"), @Field("upperPackageName"), @Field("upperName")}),
})
public class JavadocClass extends JavadocElement {
  @Id
  private ObjectId id;

  private String packageName;
  private String upperPackageName;
  private String name;
  private String upperName;
  private ObjectId superClassId;
  private List<JavadocMethod> methods = new ArrayList<>();
  private List<JavadocField> fields = new ArrayList<>();

  public JavadocClass() { }

  public JavadocClass(final JavadocApi api, final String pkg, final String name) {
    packageName = pkg;
    this.name = name;
    setApiId(api.getId());
    setDirectUrl(api.getBaseUrl() + pkg.replace('.', '/') + "/" + name + ".html");
    setLongUrl(api.getBaseUrl() + "index.html?" + pkg.replace('.', '/') + "/" + name + ".html");
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(final ObjectId classId) {
    id = classId;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(final String name) {
    packageName = name;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public ObjectId getSuperClassId() {
    return superClassId;
  }

  public void setSuperClassId(final ObjectId javadocClassId) {
    superClassId = javadocClassId;
  }

  public void setSuperClassId(final JavadocClass javadocClass) {
    superClassId = javadocClass.getId();
  }

  public String getUpperName() {
    return upperName;
  }

  public void setUpperName(final String upperName) {
    this.upperName = upperName;
  }

  public String getUpperPackageName() {
    return upperPackageName;
  }

  public void setUpperPackageName(final String upperPackageName) {
    this.upperPackageName = upperPackageName;
  }

  @PrePersist
  public void uppers() {
    upperName = name.toUpperCase();
    upperPackageName = packageName.toUpperCase();
  }

  @Override
  public String toString() {
    return packageName + "." + name;
  }
}