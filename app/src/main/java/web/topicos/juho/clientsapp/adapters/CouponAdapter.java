package web.topicos.juho.clientsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import web.topicos.juho.clientsapp.models.Coupon;
import web.topicos.juho.clientsapp.R;

/**
 * Created by juho on 29/11/17.
 */

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponHolder> {

    Context context;
    List<Coupon> couponlist = new ArrayList<>();

    public CouponAdapter(Context context, List<Coupon> couponList){
        this.context = context;
        this.couponlist = couponList;
    }

    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coupon_row, parent, false);
        CouponHolder couponHolder = new CouponHolder(view);
        return couponHolder;
    }

    @Override
    public void onBindViewHolder(CouponHolder holder, int position) {
        Coupon coupon = couponlist.get(position);
        holder.tvCouponCode.setText(coupon.getCode());
        holder.tvCouponEndDate.setText(coupon.getEnd_date());
        holder.tvCouponDescription.setText(coupon.getDescription());
    }

    @Override
    public int getItemCount() {
        return couponlist.size();
    }

    class CouponHolder extends RecyclerView.ViewHolder{
        TextView tvCouponCode, tvCouponEndDate, tvCouponDescription;
        public CouponHolder(View itemView) {
            super(itemView);
            tvCouponCode     = itemView.findViewById(R.id.tv_coupon_code);
            tvCouponEndDate = itemView.findViewById(R.id.tv_coupon_end_date);
            tvCouponDescription = itemView.findViewById(R.id.tv_coupon_desc);
        }
    }

}