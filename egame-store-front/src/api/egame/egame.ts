import { defHttp } from '/@/utils/http/axios';

enum Api {
  GAME_TYPE_LIST = '/game/type/all',
  GAME_LIST = '/game/all',
  GAME_CONSOLE_LIST = '/console/all',
  ORDER_LIST = '/order/all',
  ASSET_GAME_LIST = '/assets/game/all',
  ASSET_CONSOLE_LIST = '/assets/console/all',
  USER_LIST = '/user/get/all',
}

/**
 * @description: Get sample list value
 */

export const getGameTypeList = (params) =>
  defHttp.get({
    url: Api.GAME_TYPE_LIST,
    params,
  });

export const getGameList = (params) =>
  defHttp.get({
    url: Api.GAME_LIST,
    params,
  });

export const getGameConsoleList = (params) =>
  defHttp.get({
    url: Api.GAME_CONSOLE_LIST,
    params,
  });

export const getOrderList = (params) =>
  defHttp.get({
    url: Api.ORDER_LIST,
    params,
  });

export const getAssetGameList = (params) =>
  defHttp.get({
    url: Api.ASSET_GAME_LIST,
    params,
  });

export const getAssetConsoleList = (params) =>
  defHttp.get({
    url: Api.ASSET_CONSOLE_LIST,
    params,
  });

export const getUserList = (params) =>
  defHttp.get({
    url: Api.USER_LIST,
    params,
  });
