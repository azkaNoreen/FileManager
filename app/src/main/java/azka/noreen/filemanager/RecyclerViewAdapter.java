package azka.noreen.filemanager;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StorageItems> StorageItemsArrayList=new ArrayList<StorageItems>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.storageitems_item,parent,false);
        return new StorageItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        StorageItems st=StorageItemsArrayList.get(position);
        StorageItemsViewHolder StorageItemsViewHolder= (StorageItemsViewHolder) holder;

        StorageItemsViewHolder.StorageItemsTextName.setText(st.getFileName());
        StorageItemsViewHolder.StorageItemsPhone.setText(st.getFilePath());
        StorageItemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=new File(st.getFileName());

                if(!(file.isDirectory()))
                {
                    Intent intent=new Intent(view.getContext(),BrowseFilesActivity.class);
                    intent.putExtra("RootPath",st.getFilePath());
                    intent.putExtra("fileName",st.getFileName());

                    view.getContext().startActivity(intent);
                }
                else{
                    Toast.makeText(view.getContext(), st.getFilePath(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return StorageItemsArrayList.size();
    }
    public void setData(ArrayList<StorageItems> StorageItemsArrayList){
        this.StorageItemsArrayList=StorageItemsArrayList;
        notifyDataSetChanged();
    }
//to find views of single list xml file
    public static class StorageItemsViewHolder extends RecyclerView.ViewHolder{

        TextView StorageItemsTextName;
        TextView StorageItemsPhone;
        public StorageItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            StorageItemsTextName=itemView.findViewById(R.id.fileName);
            StorageItemsPhone=itemView.findViewById(R.id.filePath);

        }
    }

}
