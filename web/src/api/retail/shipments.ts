import {defHttp} from '/@/utils/http/axios';
import {BaseDataResp, BaseResp} from "@/api/model/baseModel";
import {
    AddOrUpdateShipmentsReq, AddOrUpdateShipmentsResp,
    QueryShipmentsReq,
    ShipmentsResp
} from "@/api/retail/model/shipmentsModel"
import {ErrorMessageMode} from "#/axios";

enum API {
    PageList = '/retail/shipments/pageList',
    List = '/retail/shipments/list',
    AddOrUpdate = '/retail/shipments/addOrUpdate',
    DeleteBatch = '/retail/shipments/deleteByIds',
    UpdateStatus = '/retail/shipments/updateStatus',
    GetDetail = '/retail/shipments/detail',
    GetLinkShipmentDetail = '/retail/shipments/getLinkShipmentDetail',
}

export function getShipmentsPageList(params: QueryShipmentsReq, mode: ErrorMessageMode = 'notice') {
    return defHttp.post<BaseDataResp<ShipmentsResp>>(
        {
            url: API.PageList,
            params,
        },
        {
            errorMessageMode: mode,
            successMessageMode: mode,
        },
    );
}

export function getShipmentsList(params: QueryShipmentsReq) {
    return defHttp.post<BaseDataResp<ShipmentsResp>>(
        {
            url: API.List,
            params,
        }
    );
}

export function addOrUpdateShipments(params: AddOrUpdateShipmentsReq) {
    return defHttp.post<BaseResp>(
        {
            url: API.AddOrUpdate,
            params,
        }
    );
}

export function deleteShipments(ids: string[]) {
    return defHttp.post<BaseResp>(
        {
            url: `${API.DeleteBatch}?ids=${ids}`,
        }
    );
}

export function updateShipmentsStatus(ids: string[], status: number) {
    return defHttp.put<BaseResp>(
        {
            url: `${API.UpdateStatus}?ids=${ids}&status=${status}`,
        }
    );
}

export function getShipmentsDetail(id: string | number) {
    return defHttp.get<BaseDataResp<AddOrUpdateShipmentsReq>>(
        {
            url: `${API.GetDetail}/${id}`,
        }
    );
}

export function getLinkShipmentsDetail(otherReceipt: string) {
    return defHttp.get<BaseDataResp<AddOrUpdateShipmentsResp>>(
        {
            url: `${API.GetLinkShipmentDetail}/${otherReceipt}`,
        }
    );
}