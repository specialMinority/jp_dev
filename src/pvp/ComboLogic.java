package pvp;

import java.util.Scanner;

/**
 * 이 게임은 2D PVP Fighting 게임으로 x축, y축, z축이 존재한다.
 * 모든 캐릭터의 체력은 100
 * 상대에게 데미지를 주려면 스킬을 사용해 공중으로 띄워야 한다.
 * 중력 보정이 존재해 한 번의 콤보로 소모시킬 수 있는 상대의 체력은 최대 49이다.
 * 스킬 분류:
 * - 궁극기 (1개): 데미지 30 / 콤보용 / 쿨타임이 매우 긴 편
 * - 주요 스킬 (3개): 데미지 15 / 기회 잡기 또는 콤보용. 슈퍼 아머 띄우기 판정. / 쿨타임이 긴 편
 * - 일반 스킬 (6개): 데미지 10 / 기회잡기도 가능하나 슈퍼 아머가 없는 띄우기 판정. 방어+콤보용 / 쿨타임이 매우 짧아서 어떤 경우에도 2개 이상은 사용 가능
 * - 평타 : 데미지1 / 콤보용 / 쿨타임 없음
 * - 피격기 : 모든 캐릭터는 1라운드 당 한 번 피격 도중에 공중에서 탈출할 수 있는 이동기를 가지고 있다. 피격기로 이동 직후에는 스킬을 사용할 수 없다.
 * 슈퍼 아머란 캐릭터가 피격받아도 띄워지지 않는 상태이다.
 * 주요 스킬은 캐릭터마다 판정의 우위가 존재한다. 슈퍼아머의 유지시간이 더 길 수록 판정이 좋다.
 * 콤보를 느리게 할 수록 회피율이 증가한다. 회피율이 올라가서 miss가 발생하면 콤보 기회를 놓친다.
 * 모든 스킬의 시전 범위는 y축 기준으로 위보다 아래로 더 넓게 설정되어 있다. 따라서 좋은 포지션은 상대방 캐릭터보다 y축 기준으로 더 위에 있는 경우다.
 * 1라운드 당 게임 시간 총 3분이며, 게임 시간이 끝나면 데미지를 더 많이 준 쪽이 승리한다.
 * 모든 캐릭터는 리스크를 지양하는 플레이를 한다.
 * 모든 캐릭터는 게임 승리를 목표로 플레이한다.
 */

public class ComboLogic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("기회를 잡아서 상대를 공중에 띄웠다.");
        System.out.println("남은 게임 시간이 1분 미만인가?");
        System.out.println("1. 1분 미만 2. 1분 이상");
        String timeLeft = sc.nextLine();
        if (timeLeft.equals("1")) {
            System.out.println("내 캐릭터가 준 데미지가 상대방이 준 데미지보다 높은가?");
            System.out.println("1. 더 높다 2. 더 적다");
            String damage = sc.nextLine();
            if (damage.equals("1")) {
                System.out.println("상대방의 체력이 얼마나 남아있는가?");
                System.out.println("1. 50미만 2. 50이상");
                String hp = sc.nextLine();
                if (hp.equals("1")) {
                    System.out.println("상대방에게 피격기가 남아있는가?");
                    System.out.println("1. 남아있다 2. 없다");
                    String hitReaction = sc.nextLine();
                    if (hitReaction.equals("1")) {
                        System.out.println("내 캐릭터의 주요스킬들이 쿨타임인가?");
                        System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                        String myMainSkillCooldown = sc.nextLine();
                        if (myMainSkillCooldown.equals("1")) {
                            System.out.println("평타 위주로 느리게 콤보한다. 상대의 탈출 예상 지점에 일반 스킬들을 깔아놓는다.");
                        } else if (myMainSkillCooldown.equals("2")) {
                            System.out.println("일반 스킬들 위주로 빠르게 콤보하여 피격기를 쓰도록 유도하고 탈출 예상 지점에 주요 스킬들을 깔아놓는다.");
                        }
                    } else if (hitReaction.equals("2")) {
                        System.out.println("내 캐릭터의 주요스킬들이 쿨타임인가?");
                        System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                        String myMainSkillCooldown = sc.nextLine();
                        if (myMainSkillCooldown.equals("1")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들로 최대한 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("일반 스킬들과 궁극기로 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            }
                        } else if (myMainSkillCooldown.equals("2")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들과 주요 스킬들로 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("주요스킬들과 궁극기로 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            }
                        }
                    }
                } else if (hp.equals("2")) {
                    System.out.println("상대방에게 피격기가 남아있는가?"); // 남은 게임 시간 1분 미만, 내 캐릭터의 누적 데미지가 우위인 상황에서 기회를 잡힌 상대방은 빨리 피격기를 써서 기회를 잡아야만 승리할 가능성이 있음
                    System.out.println("1. 남아있다 2. 없다");
                    String hitReaction = sc.nextLine();
                    if (hitReaction.equals("1")) {
                        System.out.println("내 캐릭터의 주요스킬들이 쿨타임인가?");
                        System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                        String myMainSkillCooldown = sc.nextLine();
                        if (myMainSkillCooldown.equals("1")) {
                            System.out.println("평타 위주로 느리게 콤보한다. 상대의 탈출 예상 지점에 일반 스킬들을 깔아놓는다.");
                        } else if (myMainSkillCooldown.equals("2")) {
                            System.out.println("일반 스킬들 위주로 빠르게 콤보하여 피격기를 쓰도록 유도하고 탈출 예상 지점에 주요 스킬들을 깔아놓는다.");
                        }
                    } else if (hitReaction.equals("2")) {
                        System.out.println("평타와 일반 스킬들 위주로 느리게 콤보하며 시간을 끈다. ComboOpportunity Logic으로 돌아간다.");
                    }
                }
            } else if (damage.equals("2")) {
                System.out.println("상대방의 체력이 얼마나 남아있는가?");
                System.out.println("1. 50미만 2. 50이상");
                String hp = sc.nextLine();
                if (hp.equals("1")) {
                    System.out.println("상대방에게 피격기가 남아있는가?");
                    System.out.println("1. 남아있다 2. 없다");
                    String hitReaction = sc.nextLine();
                    if (hitReaction.equals("1")) {
                        System.out.println("내 캐릭터의 주요스킬들이 쿨타임인가?");
                        System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                        String myMainSkillCooldown = sc.nextLine();
                        if (myMainSkillCooldown.equals("1")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들로 최대한 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 일반 스킬들을 깔아놓는다.");
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("일반 스킬들과 궁극기로 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 일반 스킬들을 깔아놓는다.");
                            }
                        } else if (myMainSkillCooldown.equals("2")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들로 최대한 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 주요 스킬들을 깔아놓는다.");
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("일반 스킬들과 궁극기로 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 주요 스킬들을 깔아놓는다.");
                            }
                        }
                    } else if (hitReaction.equals("2")) {
                        System.out.println("내 캐릭터의 주요스킬들이 쿨타임인가?");
                        System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                        String myMainSkillCooldown = sc.nextLine();
                        if (myMainSkillCooldown.equals("1")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들로 최대한 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("일반 스킬들과 궁극기로 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            }
                        } else if (myMainSkillCooldown.equals("2")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("주요 스킬들로 최대한 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("주요스킬들과 궁극기로 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                                System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                                String comboOutcome = sc.nextLine();
                                if (comboOutcome.equals("1")) {
                                    System.out.println("게임 승리");
                                } else if (comboOutcome.equals("2")) {
                                    System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                                }
                            }
                        }
                    }
                } else if (hp.equals("2")) { //남은 시간 1분 미만, 누적 데미지가 내 캐릭터보다 우위인데가 이 콤보로 죽지 않는 체력을 가진 상대 캐릭터가 피격기를 쓸 이유가 없기 때문에 고려하지 않음
                    System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                    System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                    String myUltimateCooldown = sc.nextLine();
                    if (myUltimateCooldown.equals("1")) {
                        System.out.println("주요 스킬들은 쿨타임 여부와 관계없이 콤보 후 빠르게 기회를 잡기 위해 아낀다.");
                        System.out.println("일반 스킬들로 최대한 빠르게 콤보한다.");
                    } else if (myUltimateCooldown.equals("2")) {
                        System.out.println("주요 스킬들은 쿨타임 여부와 관계없이 콤보 후 빠르게 기회를 잡기 위해 아낀다.");
                        System.out.println("일반 스킬들과 궁극기로 최대한 빠르게 콤보한다.");
                    }
                }
            }
        } else if (timeLeft.equals("2")) {
            System.out.println("상대방의 체력이 얼마나 남아있는가?");
            System.out.println("1. 50미만 2. 50이상");
            String hp = sc.nextLine();
            if (hp.equals("1")) {
                System.out.println("상대방에게 피격기가 남아있는가?");
                System.out.println("1. 남아있다 2. 없다");
                String hitReaction = sc.nextLine();
                if (hitReaction.equals("1")) { // 1분 이상 체력 50미만 피격기 있음
                    System.out.println("내 캐릭터의 주요 스킬들이 쿨타임인가?");
                    System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                    String myMainSkillCooldown = sc.nextLine();
                    if (myMainSkillCooldown.equals("1")) {
                        System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                        System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                        String myUltimateCooldown = sc.nextLine();
                        if (myUltimateCooldown.equals("1")) {
                            System.out.println("일반 스킬들로 최대한 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 일반 스킬들을 깔아놓는다.");
                        } else if (myUltimateCooldown.equals("2")) {
                            System.out.println("일반 스킬들과 궁극기로 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 일반 스킬들을 깔아놓는다.");
                        }
                    } else if (myMainSkillCooldown.equals("2")) {
                        System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                        System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                        String myUltimateCooldown = sc.nextLine();
                        if (myUltimateCooldown.equals("1")) {
                            System.out.println("일반 스킬들로 최대한 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 주요 스킬들을 깔아놓는다.");
                        } else if (myUltimateCooldown.equals("2")) {
                            System.out.println("일반 스킬들과 궁금극기로 빠르게 콤보해서 피격기를 쓰도록 유도한다. 탈출 예상 지점에 주요 스킬들을 깔아놓는다.");
                        }
                    }
                } else if (hitReaction.equals("2")) {
                System.out.println("내 캐릭터의 주요 스킬들이 쿨타임인가?");
                System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                String myMainSkillCooldown = sc.nextLine();
                if (myMainSkillCooldown.equals("1")) {
                    System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                    System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                    String myUltimateCooldown = sc.nextLine();
                    if (myUltimateCooldown.equals("1")) {
                        System.out.println("일반 스킬들로 최대한 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                        System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                        String comboOutcome = sc.nextLine();
                        if (comboOutcome.equals("1")) {
                            System.out.println("게임 승리");
                        } else if (comboOutcome.equals("2")) {
                            System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                        }
                    } else if (myUltimateCooldown.equals("2")) {
                        System.out.println("일반 스킬들과 궁극기로 최대한 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                        System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                        String comboOutcome = sc.nextLine();
                        if (comboOutcome.equals("1")) {
                            System.out.println("게임 승리");
                        } else if (comboOutcome.equals("2")) {
                            System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                        }
                    }
                } else if (myMainSkillCooldown.equals("2")) {
                    System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                    System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                    String myUltimateCooldown = sc.nextLine();
                    if (myUltimateCooldown.equals("1")) {
                        System.out.println("주요 스킬들로 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                        System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                        String comboOutcome = sc.nextLine();
                        if (comboOutcome.equals("1")) {
                            System.out.println("게임 승리");
                        } else if (comboOutcome.equals("2")) {
                            System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                        }
                    } else if (myUltimateCooldown.equals("2")) {
                        System.out.println("주요 스킬들과 궁극기를 사용해 빠르게 콤보하여 상대 캐릭터를 game over 시킨다.");
                        System.out.println("1. 상대방 캐릭터 사망으로 game over 2. miss가 발생해 콤보기회를 놓쳤다.");
                        String comboOutcome = sc.nextLine();
                        if (comboOutcome.equals("1")) {
                            System.out.println("게임 승리");
                        } else if (comboOutcome.equals("2")) {
                            System.out.println("역으로 기회잡힐 가능성이 있기 때문에 miss가 발생한 시점에 상대로부터 빠르게 멀어진다. ComboOpportunity Logic으로 돌아간다.");
                        }
                    }
                }}
            } else if (hp.equals("2")) { //1분 이상 체력 50 이상으로 기회를 잡히더라도 피격기를 쓸 이유 없음
                System.out.println("상대방의 주요 스킬이 쿨타임인가?");
                System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                String opponentMainSkillCooldown = sc.nextLine();
                if (opponentMainSkillCooldown.equals("1")) {
                    System.out.println("내 캐릭터의 주요 스킬은 쿨타임인가?");
                    System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                    String myMainSkillCooldown = sc.nextLine();
                    if (myMainSkillCooldown.equals("1")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들을 사용해 최대한 빠르게 데미지를 넣는다.");
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("궁극기를 사용해 빠르게 데미지를 넣는다.");
                            }
                        } else if (hitbox.equals("2")) {
                            System.out.println("주요 스킬 쿨타임을 벌기 위해 평타로 느리게 콤보한다.");
                        }
                    } else if (myMainSkillCooldown.equals("2")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들을 사용해 최대한 빠르게 데미지를 넣는다.");
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("일반 스킬들과 궁극기를 사용해 빠르게 데미지를 넣는다.");
                            }
                        } else if (hitbox.equals("2")) {
                            System.out.println("주요 스킬들과 일반 스킬들을 사용해 빠르게 데미지를 넣는다.");
                        }
                    }
                } else if (opponentMainSkillCooldown.equals("2")) {
                    System.out.println("내 캐릭터의 주요 스킬은 쿨타임인가?");
                    System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                    String myMainSkillCooldown = sc.nextLine();
                    if (myMainSkillCooldown.equals("1")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("일반 스킬들을 사용해 최대한 빠르게 데미지를 넣는다.");
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("일반 스킬들과 궁극기를 사용해 빠르게 데미지를 넣는다.");
                            }
                        } else if (hitbox.equals("2")) {
                            System.out.println("주요 스킬 쿨타임을 벌기 위해 평타로 느리게 콤보한다.");
                        }
                    } else if (myMainSkillCooldown.equals("2")) {
                        System.out.println("상대방의 주요 스킬이 내 캐릭터의 주요 스킬보다 판정이 좋은가?");
                        System.out.println("1. 좋다 2. 안 좋다");
                        String hitbox = sc.nextLine();
                        if (hitbox.equals("1")) {
                            System.out.println("내 캐릭터의 궁극기가 쿨타임인가?");
                            System.out.println("1. 쿨타임이다 2. 쿨타임이 아니다");
                            String myUltimateCooldown = sc.nextLine();
                            if (myUltimateCooldown.equals("1")) {
                                System.out.println("주요 스킬들과 일반 스킬들을 사용해 빠르게 데미지를 넣는다.");
                            } else if (myUltimateCooldown.equals("2")) {
                                System.out.println("주요 스킬들과 궁극기를 사용해 빠르게 데미지를 넣는다.");
                            }
                        } else if (hitbox.equals("2")) {
                            System.out.println("일반 스킬들을 사용해 최대한 빠르게 데미지를 넣는다.");
                        }
                    }
                }
            }
        }
    }
}


