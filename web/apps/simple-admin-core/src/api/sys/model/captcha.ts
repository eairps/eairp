export interface CaptchaResp {
  captchaId: string;
  imagePath: string;
}

export interface GetEmailCaptchaReq {
  email: string;
}

export interface GetSmsCaptchaReq {
  phoneNumber: string;
}
