import {defHttp} from '/@/utils/http/axios';
import {BaseDataResp, BaseResp} from "@/api/model/baseModel";
import { QueryShipmentsReq } from "@/api/retail/model/shipmentsModel"
import {
    AddOrUpdateRefundReq,
    RefundResp
} from "@/api/retail/model/refundModel"

enum API {
    PageList = '/retail/refund/pageList',
    AddOrUpdate = '/retail/refund/addOrUpdate',
    GetDetail = '/retail/refund/detail',
    UpdateStatus = '/retail/refund/updateStatus',
    Delete = '/retail/refund/deleteByIds',
}

export function getRefundPageList(params: QueryShipmentsReq) {
    return defHttp.post<BaseDataResp<RefundResp>>(
        {
            url: API.PageList,
            params,
        }
    );
}

export function addOrUpdateRefund(params: AddOrUpdateRefundReq) {
    return defHttp.post<BaseResp>(
        {
            url: API.AddOrUpdate,
            params,
        }
    );
}

export function getRefundDetail(id: string) {
    return defHttp.get<BaseDataResp<AddOrUpdateRefundReq>>(
        {
            url: `${API.GetDetail}/${id}`,
        }
    );
}

export function updateRefundStatus(ids: string[], status: number) {
    return defHttp.put<BaseResp>(
        {
            url: `${API.UpdateStatus}?ids=${ids}&status=${status}`,
        }
    );
}

export function deleteRefund(ids: string[]) {
    return defHttp.post<BaseResp>(
        {
            url: `${API.Delete}?ids=${ids}`,
        }
    );
}