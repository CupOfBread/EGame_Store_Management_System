export interface GrowCardItem {
  icon: string;
  title: string;
  value: number;
  total: number;
  color: string;
  action: string;
}

export const growCardList: GrowCardItem[] = [
  // {
  //   title: '访问数',
  //   icon: 'visit-count|svg',
  //   value: 2000,
  //   total: 120000,
  //   color: 'green',
  //   action: '月',
  // },
  {
    title: '成交额',
    icon: 'total-sales|svg',
    value: 34123,
    total: 2242,
    color: 'blue',
    action: '月',
  },
  // {
  //   title: '下载数',
  //   icon: 'download-count|svg',
  //   value: 8000,
  //   total: 120000,
  //   color: 'orange',
  //   action: '周',
  // },
  {
    title: '成交数',
    icon: 'transaction|svg',
    value: 31233,
    total: 321254,
    color: 'purple',
    action: '年',
  },
];
