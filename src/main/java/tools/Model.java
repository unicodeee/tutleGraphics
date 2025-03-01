package tools;

public abstract class Model {
    private String fileName;
    private boolean unsavedChanges;
    protected boolean saveAs;  // Add this flag to track "Save As" state

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fName) {
        this.fileName = fName;
        setUnsavedChanges(true);  // Changes are made, so set unsavedChanges to true
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean unsavedChanges) {
        this.unsavedChanges = unsavedChanges;
    }

    public boolean getSaveAs() {
        return saveAs;
    }

    public void setSaveAs(boolean saveAs) {
        this.saveAs = saveAs;
    }
}
