package azka.noreen.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class BrowseFilesActivity extends AppCompatActivity {
    RecyclerView recycleView;
    ArrayList<StorageItems> studentArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_files);
        recycleView=findViewById(R.id.rv);


//        studentArrayList= initStudentPrameterList();

        Intent intent=getIntent();
        String rootPath=intent.getStringExtra("RootPath");
        File file=new File(rootPath);
        File[] childFiles = file.listFiles();

        for(int i=0; i<childFiles.length; i++){
            String fName=childFiles[i].getName();
            String fPath=childFiles[i].getPath();
            StorageItems storageItems=new StorageItems(fName,fPath);
            studentArrayList.add(storageItems);
        }
        InitRecycleView();
    }
    public void InitRecycleView(){
        RecyclerViewAdapter rva=new RecyclerViewAdapter();
        recycleView.setAdapter(rva);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        rva.setData(studentArrayList);

    }
}